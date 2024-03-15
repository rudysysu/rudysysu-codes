package com.github.rudysysu.codes.thriftclient;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class MainApp {
    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static final String SERVER_IP = "10.62.15.109";
    public static final int SERVER_PORT = 6910;
    public static final int TIMEOUT = 6000;

    @PostConstruct
    public void queryUserConsumeStat() {
        try (TTransport transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT))) {
            TProtocol protocol = new TBinaryProtocol(transport);
            TBizRiskControlStatService.Client client = new TBizRiskControlStatService.Client(protocol);
            transport.open();
            TBizRiskControlUserConsumeStat stat = client.queryUserConsumeStat(50047715, 30);
            LOG.info("stat: {}", stat);
        } catch (TException e) {
            LOG.error(e.toString(), e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
