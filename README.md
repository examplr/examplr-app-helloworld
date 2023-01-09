# examplr-app-helloworld

A super simply Spring Boot app that simply response with "Hello World!" to any GET request.

This is designed to be published as an ECR container and serve traffic in an ECS Fargate cluster behind an ALB.

To use this project:

```
./gradlew build
docker build -t 937423686755.dkr.ecr.us-east-1.amazonaws.com/helloworld:latest .

aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 937423686755.dkr.ecr.us-east-1.amazonaws.com/helloworld:latest

export AWS_ACCESS_KEY_ID={tfuer@examplr-dev creds}
export AWS_SECRET_ACCESS_KEY={tfuer@examplr-dev creds}
export AWS_DEFAULT_REGION=us-east-1

docker push 937423686755.dkr.ecr.us-east-1.amazonaws.com/helloworld:latest
```

Finally, you need to force the ECS cluster to update the and redeploy the service to pick up the changed container
```
aws ecs update-service --cluster dev-example-cluster1 --service dev-example-helloworld --force-new-deployment
```

