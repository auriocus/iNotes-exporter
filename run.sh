cd target
java '-Demail.folder.id=($All)' -Djava.util.logging.ConsoleHandler.level=TRACE -Dpop3.port=1027 -jar iNotes-exporter-1.8-jar-with-dependencies.jar POP3Server
