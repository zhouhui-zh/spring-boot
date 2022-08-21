package com.hui.springboot.mysql.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-21 23:47
 */
public class DataHandlerClient extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(DataHandlerClient.class);
    private Channel channel;

    public DataHandlerClient(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取读取的数据， 是一个缓冲。
        ByteBuf readBuffer = (ByteBuf) msg;
        byte[] bytes = new byte[readBuffer.readableBytes()];
        readBuffer.getBytes(readBuffer.readerIndex(), bytes);
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(bytes, readBuffer.readerIndex(), readBuffer.readableBytes());
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();

        if (StringUtils.isBlank(encoding)) {
            encoding = "UTF-8";
        }
        logger.info(" data encoding =[{}]-->{}", encoding, readBuffer.toString(Charset.forName(encoding)));


        //  logger.info("get data: " + convertByteBufToString(readBuffer));

        //这里的复位不能省略,不然会因为计数器问题报错.
        readBuffer.retain();
        //将数据发到远程客户端那边
        channel.writeAndFlush(readBuffer);
        //channel.writeAndFlush("ok");

    }


}
