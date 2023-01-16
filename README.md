# examplr-app-helloworld

A super simply Spring Boot app that simply response with "Hello World!" to any GET request.

This is designed to be published as an ECR container and serve traffic in an ECS Fargate cluster behind an ALB.

To use this project:


```
./gradlew build
docker build -t 223609663012.dkr.ecr.us-east-1.amazonaws.com/helloworld:latest .


export AWS_ACCESS_KEY_ID=[REPLACE WITH tfuser@examplr-devops accessKey]
export AWS_SECRET_ACCESS_KEY=[REPLACE WITH tfuser@examplr-devops secretKey]

aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 223609663012.dkr.ecr.us-east-1.amazonaws.com/helloworld:latest

docker push 223609663012.dkr.ecr.us-east-1.amazonaws.com/helloworld:latest
docker push 223609663012.dkr.ecr.us-east-1.amazonaws.com/helloworld:`git log -1 --format=%h``

export AWS_ACCESS_KEY_ID=[REPLACE WITH tfuser@examplr-development accessKey]
export AWS_SECRET_ACCESS_KEY=[REPLACE WITH tfuser@examplr-development secretKey]

aws ecs update-service --cluster dev-example-cluster1 --service dev-example-helloworld --force-new-deployment
```


