package com.hui.springboot.mysql.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-21 23:47
 */
public class DataHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(DataHandler.class);
    private Channel channel;

    public DataHandler(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取读取的数据， 是一个缓冲。
        ByteBuf readBuffer = (ByteBuf) msg;

        //logger.info("get data: " + readBuffer.toString(CharsetUtil.UTF_8));


        //  logger.info("get data: " + convertByteBufToString(readBuffer));
        convertByteBufToString(readBuffer);
        //这里的复位不能省略,不然会因为计数器问题报错.
        readBuffer.retain();
        //将数据发到远程客户端那边
        channel.writeAndFlush(readBuffer);
        //channel.writeAndFlush("ok");

    }


    public String convertByteBufToString(ByteBuf buf) {
        System.out.println();
        String str;
        if (buf.hasArray()) { // 处理堆缓冲区
            logger.info("处理堆缓冲区");
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            logger.info("处理直接缓冲区以及复合缓冲区");
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            byte[] tmp = new byte[buf.readableBytes()];
            int i = 0;
            int j = 0;
            byte[] tmp2 = new byte[buf.readableBytes()];

            for (byte b : bytes) {
                /*if (i == 0) {
                    if (b != 64) {
                        return null;
                    }
                }
                if (i == 0 && b == 7) {//响铃 不解析
                    return null;
                }*/
                byte aByte = b;
                if (i == 0 && aByte < 0) {//第一位是负
                    continue;
                }

                if (aByte < 32 && aByte > -1 && aByte != 10){//10换行,中间负数中文
                    aByte = 0;
                }


                if (i == 0 && aByte == 0) {//第一位是0
                    continue;
                }

                if (i > 0 && tmp[i - 1] == 0 && aByte == 0) { //连续空格
                    continue;
                }

                if (aByte != '\u0006'
                        && aByte != '�'
                        && aByte != '\u001F'
                        && aByte != 127
                ) {
                    tmp[i++] = aByte;
                    tmp2[j++] = aByte;

                }

               /* if (aByte > 31
                        && aByte != 0
                        && aByte != 1
                        && aByte != '\u0006'
                        && aByte != 5
                        && aByte != -1
                        && aByte != '�'
                        && aByte != '\u001F'
                        && aByte != 127
                ) {
                    tmp[i++] = aByte;
                    tmp2[j++] = aByte;

                }*/

            }
            int k = tmp.length;
            for (int i1 = 0; i1 < tmp.length; i1++) {
                if (tmp[tmp.length - i1 - 1] != 0) {
                    k = tmp.length - i1;
                    break;
                }
            }
            if (k != tmp.length) {
                tmp2 = new byte[k];
                for (i = 0; i < k; i++) {
                    tmp2[i] = tmp[i];
                }
            }
            logger.info("-转前1-->{}", bytes);
            logger.info("-转前2-->{}", new String(bytes));
            bytes = tmp2;
            logger.info("-转后1-->{}", bytes);
            // str = new String(bytes, 0, buf.readableBytes());
            str = new String(bytes);
        }

        logger.info("-转后2-->{}", str);
        System.out.println();
        return str;

    }
}
