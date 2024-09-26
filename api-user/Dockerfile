FROM eclipse-temurin:21

# 컨테이너 내 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 컨테이너로 복사
COPY api-user/build/libs/api-user*.jar api-user.jar

# db-config.yml 파일을 컨테이너로 복사
COPY storage/src/main/resources/db-config.yml /app/config/db-config.yml

# 애플리케이션이 사용할 포트를 노출
EXPOSE 8081

# JAR 파일을 실행
ENTRYPOINT ["java", "-jar", "api-user.jar", "--spring.config.additional-location=file:/app/config/db-config.yml"]