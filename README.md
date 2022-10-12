### 구성
    react
    springboot
    
### 빌드
    ./mvnw clean install jib:dockerBuild -Dimage=fullstack:v1
    ./mvnw clean install jib:build -Djib.to.image=deneb1593/spring-react-fullstack:v1

### 실행
    docker run --name fullstack -p 8080:8080 fullstack:v1