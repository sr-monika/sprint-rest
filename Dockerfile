FROM openjdk:17-alpine

WORKDIR /var/app/

COPY ./run-service.sh ./target/edip-inventory-0.0.1-SNAPSHOT.jar /var/app/

RUN chmod +x /var/app/run-service.sh

RUN apk update \
&& apk add shadow=4.8.1-r0 \
&& groupadd order \
&& useradd -g order order-user \
&& chown -R order-user:order /var/app/

RUN apk --no-cache add curl

USER order-user

ENV JAVA_OPTS=""

EXPOSE 8080

ENTRYPOINT ["/var/app/run-service.sh"]