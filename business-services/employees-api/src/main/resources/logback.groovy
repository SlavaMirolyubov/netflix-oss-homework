import net.logstash.logback.appender.LogstashTcpSocketAppender
import net.logstash.logback.encoder.LogstashEncoder

def appHost = "localhost"
def appName = "elk-demo-service"
def logstashHost = "localhost"//System.getenv("LOGSTASH_HOST")
def logstashPort = 5000//System.getenv("LOGSTASH_PORT")

println "=" * 80
println """
    APP NAME        :$appName
    APP HOST        :$appHost
    LOGSTASH_HOST   :$logstashHost
    LOGSTASH_PORT   :$logstashPort
"""
println "=" * 80

def appenderList = []

if (logstashHost) {
    appender("logstash", LogstashTcpSocketAppender) {
        remoteHost =  logstashHost
        port = logstashPort.toInteger()

        encoder(LogstashEncoder) {
//            customFields = toJson([app_id: appName, app_host: appHost])
        }
    }
    appenderList << 'logstash'
}

appender("console", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%-4relative %d %-5level [ %t ] %-5logger{13} | %m %n"
    }
}
appenderList << 'console'

root(INFO, appenderList)