#ベースイメージ（コンテナのOS）を指定
FROM amazonlinux:2

#java11のインストール
RUN yum install -y java-11-amazon-corretto

#JAVA_HOME(環境変数)を設定
ENV JAVA_HOME /usr/lib/jvm/java-11-amazon-corretto.x86_64

#＜前提＞tomcat9、jdbc、warファイル、このDockerfile をEC2上の同じ階層に置いてあること
#tomcatのインストール
ADD ./apache-tomcat-9.0.58.tar.gz /opt/

#JDBCのインストール
ADD ./mysql-connector-java.jar /opt/apache-tomcat-9.0.58/bin/

#ソースコードのフォルダをtomcat配下にコピー？
COPY ./Schedule_mysql /opt/apache-tomcat-9.0.58/webapps/

#tomcat起動
CMD ["/opt/apache-tomcat-9.0.58/bin/catalina.sh", "run"]