# Deployando a aplicação

- Instalar o tomcat: https://tomcat.apache.org/download-90.cgi;
- Definir uma porta (8085);
- Definir o Tomcat para inicializar automaticamente (ou na configuração ou no C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin/tomcat9w)
- Verificar se já há java instalado (java -version), se não, instalar (https://www.java.com/pt-BR/download/)
- Com ele instalado, ir em C:\Program Files\Apache Software Foundation\Tomcat 9.0\conf no arquivo tomcat-users e descomentar os usuários;
- Na aplicação, rodar um mvn package pra criar o .war
- Ir em http://localhost:8085/manager/html (usar a porta definida do tomcat) e selecionar o .war criado pela maven goal
- Em C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps apagar a pasta ROOT e renomear o war para ROOT.war