### STAGE 1: Build ###
FROM node:lts-alpine AS build

#### make the 'app' folder the current working directory
WORKDIR /usr/src/app

#### copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

#### install angular cli
RUN npm install -g @angular/cli

#### install project dependencies
RUN npm install

#### copy things
COPY . .

ARG BACKEND_ENDPOINT=/api
ARG DATABASE_ENDPOINT=/db

# ARG BACKEND_ENDPOINT=/api

ENV BACKEND_ENDPOINT=$BACKEND_ENDPOINT
ENV DATABASE_ENDPOINT=$DATABASE_ENDPOINT

#### generate build --prod
RUN npm run build

### STAGE 2: Run ###
FROM nginxinc/nginx-unprivileged

#### copy nginx conf
COPY ./conf/nginx.conf /etc/nginx/conf.d/default.conf

#### copy artifact build from the 'build environment'
COPY --from=build /usr/src/app/dist/vote-application-frontend /usr/share/nginx/html

#### don't know what this is, but seems cool and techy
CMD ["nginx", "-g", "daemon off;"]
