cd target
TRUSTSTORE=~/bin/webmailtrust
java '-Demail.folder.id=($All)' -Djava.util.logging.ConsoleHandler.level=TRACE -Djavax.net.ssl.trustStore=$TRUSTSTORE -Djavax.net.ssl.trustStorePassword=123456 -Dpop3.port=1027 -jar iNotes-exporter-1.8-jar-with-dependencies.jar POP3Server
