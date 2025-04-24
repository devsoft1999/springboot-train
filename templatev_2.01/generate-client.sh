#!/bin/bash

echo "🕒 Waiting for Spring Boot to be ready on http://localhost:8080..."
until curl -s http://localhost:8080/actuator/health | grep -q UP; do
  echo "⏳ Waiting for Spring Boot..."
  sleep 2
done

echo "📤 Exporting OpenAPI spec..."
curl -s http://localhost:8080/v3/api-docs.yaml -o openapi/api.yaml

if grep -q "paths:" openapi/api.yaml; then
  echo "🔧 Generating TypeScript SDK..."
  npx @openapitools/openapi-generator-cli generate \
    -i openapi/api.yaml \
    -g typescript-axios \
    -o generated-client \
    --skip-validate-spec \
    --additional-properties=supportsES6=true,withSeparateModelsAndApi=true,apiPackage=api,modelPackage=model
  echo "✅ SDK ready at ./generated-client"
else
  echo "❌ ERROR: Spec export failed or is empty!"
  cat openapi/api.yaml
fi
