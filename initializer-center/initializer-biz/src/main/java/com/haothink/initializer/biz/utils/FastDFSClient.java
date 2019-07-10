package com.haothink.initializer.biz.utils;

import com.haothink.common.domain.Result;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author wanghao
 * @date 2019年06月23日 17:27
 * description:
 */
@Component
public class FastDFSClient {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    private static final String PROTOCOL = "http://";
    private static final String SEPARATOR = "/";

    /**
     * 配置文件
     */
    @Value("${group.name}")
    private String fastDfsGroupName;
    @Value("${connect_timeout_in_seconds}")
    private int connectionTimeoutInSeconds;
    @Value("${network_timeout_in_seconds}")
    private int networkTimeoutInSeconds;
    @Value("${charset}")
    private String gCharset;
    @Value("${http_anti_steal_token}")
    private boolean httpAntiStealToken;
    @Value("${http_secret_key}")
    private String httpSecretKey;
    @Value("${http_tracker_http_port}")
    private int httpTrackerHttpPort;
    @Value("${tracker_servers}")
    private String trackerServers;
    @Value("${remote.domain}")
    private String fastDfsDomain;


    @PostConstruct
    public void getInstance() {
        try {
            Properties props = new Properties();
            props.put(ClientGlobal.CONF_KEY_CONNECT_TIMEOUT, connectionTimeoutInSeconds);
            props.put(ClientGlobal.CONF_KEY_NETWORK_TIMEOUT, networkTimeoutInSeconds);
            props.put(ClientGlobal.PROP_KEY_CHARSET, gCharset);
            props.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, httpAntiStealToken);
            props.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, httpSecretKey);
            props.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, httpTrackerHttpPort);
            props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, trackerServers);
            ClientGlobal.initByProperties(props);

        } catch (Exception e) {
            logger.error("FastDFS初始化-加载配置项异常", e);
        }
    }

    private StorageClient getStorageClient() {
        StorageClient storageClient = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, null);

        } catch (Exception e) {
            logger.error("FastDFS初始化-初始化客户端异常", e);
        }
        return storageClient;
    }

    /**
     * 文件的上传 返回文件上传的http地址
     *
     * @param content    文件内容
     * @param suffixName 后缀名
     * @return 返回文件上传的http地址
     */
    public Result upload(byte[] content, String suffixName, String fileName) {
        String[] uploadResults;
        try {
            StorageClient storageClient = getStorageClient();
            NameValuePair[] valuePairs = new NameValuePair[]{new NameValuePair("fileName", fileName)};
            uploadResults = storageClient.upload_file(fastDfsGroupName, content, suffixName, valuePairs);
            String remoteFileName = uploadResults[1];
            return Result.buildSuccessResult(remoteFileName);
        } catch (Exception e) {
            logger.error("上传失败:传输的过程失败!", e);
            return Result.buildFailedResult("上传失败:传输的过程失败!");
        }
    }

    public void setFastDfsGroupName(String fastDfsGroupName) {
        this.fastDfsGroupName = fastDfsGroupName;
    }

    public void setConnectionTimeoutInSeconds(int connectionTimeoutInSeconds) {
        this.connectionTimeoutInSeconds = connectionTimeoutInSeconds;
    }

    public void setNetworkTimeoutInSeconds(int networkTimeoutInSeconds) {
        this.networkTimeoutInSeconds = networkTimeoutInSeconds;
    }

    public void setgCharset(String gCharset) {
        this.gCharset = gCharset;
    }

    public void setHttpAntiStealToken(boolean httpAntiStealToken) {
        this.httpAntiStealToken = httpAntiStealToken;
    }

    public void setHttpSecretKey(String httpSecretKey) {
        this.httpSecretKey = httpSecretKey;
    }

    public void setHttpTrackerHttpPort(int httpTrackerHttpPort) {
        this.httpTrackerHttpPort = httpTrackerHttpPort;
    }

    public void setTrackerServers(String trackerServers) {
        this.trackerServers = trackerServers;
    }

    public void setFastDfsDomain(String fastDfsDomain) {
        this.fastDfsDomain = fastDfsDomain;
    }

    public String getFastDfsDomain() {
        return fastDfsDomain;
    }

    public byte[] getFile(String path) {
        StorageClient storageClient = getStorageClient();
        try {
            return storageClient.download_file(fastDfsGroupName, path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getFileName(String path){
        StorageClient storageClient = getStorageClient();
        try {
            NameValuePair[] nameValuePairs = storageClient.get_metadata(fastDfsGroupName, path);
            return nameValuePairs[0].getValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
