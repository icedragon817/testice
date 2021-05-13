package com.alibaba.game.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.game.model.Metric;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.cms.model.v20180308.NodeInstallRequest;
import com.aliyuncs.cms.model.v20180308.QueryMetricLastResponse;
import com.aliyuncs.dds.model.v20151201.DescribeDBInstanceAttributeRequest;
import com.aliyuncs.dds.model.v20151201.DescribeRegionsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.rds.model.v20140815.DescribeDBInstancesRequest;

/**
 * @author libinlong
 */
public class TestAliCloudAPI {

    public static void main(String[] args) throws ClassNotFoundException {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
            // 您的可用区ID
            "cn-hangzhou",
            // 您的Access Key ID
            "LTAIxV5NcEzJW1Vx",
            // 您的Access Key Secret
            "gz3ag5ATAEp1RjVqgCAMtXddOV1DNG");
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建API请求并设置参数
        //NodeUninstallRequest request = new NodeUninstallRequest();
        DescribeDBInstancesRequest request = new DescribeDBInstancesRequest();

        request.setRegionId("cn-shanghai");
        request.setDBInstanceId("rm-uf6d5h7b2o8a0hz15");
        //request.setPageSize(100);
        //request.setRegionId("cn-shanghai");
        //request.setProject("acs_vpc_eip");
        //request.setMetric("net_tx.rate");
        //request.setAcceptFormat(FormatType.JSON);

        try {
            long start = System.currentTimeMillis();

            AcsResponse response = null;
            for (int i=0;i<1;i++) {
                response = client.getAcsResponse(request);
            }

            axisResponse(response);
            //AcsResponse response = client.getAcsResponse(request);
            long end = System.currentTimeMillis();
            System.out.println("total cost " + (end - start) + " ms");

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static void axisResponse(AcsResponse response) {
        if (response != null) {

            String datapoints = ((QueryMetricLastResponse) response).getDatapoints();
            List<Metric> metrics = JSONArray.parseArray(datapoints, Metric.class);
            System.out.println(metrics);
        }
    }
}
