# Bucket S3 para almacenar el JAR
resource "aws_s3_bucket" "lambda_bucket" {
  bucket = "springboot-lambda-user-crud-bucket"
}

# Subir el JAR a S3
resource "aws_s3_object" "lambda_jar" {
  bucket = aws_s3_bucket.lambda_bucket.bucket
  key    = "springboot-user-crud-app.jar"
  source = "../target/users-1.0.jar"  # Cambia por la ruta donde esté tu JAR
}

# Crear un rol para la Lambda con permisos para invocar la función y logs
resource "aws_iam_role" "lambda_execution_role" {
  name = "lambda_execution_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action = "sts:AssumeRole",
      Effect = "Allow",
      Principal = {
        Service = "lambda.amazonaws.com"
      }
    }]
  })
}

# Adjuntar políticas al rol de la Lambda para que pueda ejecutarse
resource "aws_iam_role_policy_attachment" "lambda_execution_policy" {
  role       = aws_iam_role.lambda_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

# Crear API Gateway Policy
resource "aws_iam_policy" "apigateway_policy" {
  name        = "APIGatewayLambdaPolicy"
  description = "Permisos para que API Gateway invoque la función Lambda"

  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Effect    = "Allow",
        Action    = [
          "apigatewayv2:CreateApi",
          "apigatewayv2:UpdateApi",
          "apigatewayv2:DeleteApi",
          "apigatewayv2:CreateIntegration",
          "apigatewayv2:UpdateIntegration",
          "apigatewayv2:DeleteIntegration",
          "apigatewayv2:CreateRoute",
          "apigatewayv2:UpdateRoute",
          "apigatewayv2:DeleteRoute",
          "apigatewayv2:CreateDeployment",
          "apigatewayv2:UpdateDeployment",
          "apigatewayv2:DeleteDeployment",
          "apigatewayv2:CreateStage",
          "apigatewayv2:UpdateStage",
          "apigatewayv2:DeleteStage"
        ],
        Resource = "*"
      }
    ]
  })
}

# Adjuntar la política de API Gateway al rol de Lambda
resource "aws_iam_role_policy_attachment" "lambda_apigateway_policy_attachment" {
  role       = aws_iam_role.lambda_execution_role.name
  policy_arn = aws_iam_policy.apigateway_policy.arn
}

# Crear la función Lambda para ejecutar la API CRUD
resource "aws_lambda_function" "crud_lambda" {
  function_name = "springboot_user-crud_lambda"
  s3_bucket     = aws_s3_bucket.lambda_bucket.bucket
  s3_key        = aws_s3_object.lambda_jar.key
  handler       = "com.chakray.users.StreamLambdaHandler"  # Cambia por el nombre del package donde tengas tu handler
  runtime       = "java17"
  role          = aws_iam_role.lambda_execution_role.arn
  memory_size   = 1024
  timeout       = 30

  environment {
    variables = {
      SPRING_PROFILES_ACTIVE = "prod"  # Cambia por el perfil de Spring si es necesario
    }
  }
}

# Crear API Gateway
resource "aws_apigatewayv2_api" "crud_api" {
  name          = "SpringBoot USER CRUD API"
  protocol_type = "HTTP"
}

# Configurar integración de API Gateway con Lambda
resource "aws_apigatewayv2_integration" "crud_integration" {
  api_id           = aws_apigatewayv2_api.crud_api.id
  integration_type = "AWS_PROXY"
  integration_uri  = aws_lambda_function.crud_lambda.invoke_arn
  payload_format_version = "2.0"
}

# Crear la ruta de API Gateway que manejará todas las solicitudes HTTP
resource "aws_apigatewayv2_route" "crud_route" {
  api_id    = aws_apigatewayv2_api.crud_api.id
  route_key = "ANY /{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.crud_integration.id}"
}

# Crear un deployment para API Gateway
resource "aws_apigatewayv2_stage" "crud_stage" {
  api_id      = aws_apigatewayv2_api.crud_api.id
  name        = "$default"
  auto_deploy = true
}

# Permitir que API Gateway invoque la Lambda
resource "aws_lambda_permission" "apigateway_lambda_permission" {
  statement_id  = "AllowAPIGatewayInvoke"
  action        = "lambda:InvokeFunction"
  function_name = aws_lambda_function.crud_lambda.function_name
  principal     = "apigateway.amazonaws.com"
  source_arn    = "${aws_apigatewayv2_api.crud_api.execution_arn}/*"
}
