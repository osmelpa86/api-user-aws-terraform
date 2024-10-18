output "api_gateway_url" {
  value       = aws_apigatewayv2_api.crud_api.api_endpoint
  description = "API Gateway URL"
}
