/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tomcat.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.MessageHandler;

import org.junit.Assert;
import org.junit.Test;

public class TestUtil {

    @Test
    public void testGetMessageTypeSimple() {
        Assert.assertEquals(
                String.class, Util.getMessageType(new SimpleMessageHandler()));
    }


    @Test
    public void testGetMessageTypeSubclass() {
        Assert.assertEquals(String.class,
                Util.getMessageType(new SubSimpleMessageHandler()));
    }


    @Test
    public void testGetMessageTypeGenericSubclass() {
        Assert.assertEquals(String.class,
                Util.getMessageType(new GenericSubMessageHandler()));
    }


    @Test
    public void testGetMessageTypeGenericMultipleSubclass() {
        Assert.assertEquals(String.class,
                Util.getMessageType(new GenericMultipleSubSubMessageHandler()));
    }


    @Test
    public void testGetMessageTypeGenericMultipleSubclassSwap() {
        Assert.assertEquals(String.class,
                Util.getMessageType(new GenericMultipleSubSubSwapMessageHandler()));
    }


    @Test
    public void testGetEncoderTypeSimple() {
        Assert.assertEquals(
                String.class, Util.getEncoderType(new SimpleEncoder()));
    }


    @Test
    public void testGetEncoderTypeSubclass() {
        Assert.assertEquals(String.class,
                Util.getEncoderType(new SubSimpleEncoder()));
    }


    @Test
    public void testGetEncoderTypeGenericSubclass() {
        Assert.assertEquals(String.class,
                Util.getEncoderType(new GenericSubEncoder()));
    }


    @Test
    public void testGetEncoderTypeGenericMultipleSubclass() {
        Assert.assertEquals(String.class,
                Util.getEncoderType(new GenericMultipleSubSubEncoder()));
    }


    @Test
    public void testGetEncoderTypeGenericMultipleSubclassSwap() {
        Assert.assertEquals(String.class,
                Util.getEncoderType(new GenericMultipleSubSubSwapEncoder()));
    }


    private static class SimpleMessageHandler
            implements MessageHandler.Basic<String> {
        @Override
        public void onMessage(String message) {
            // NO-OP
        }
    }


    private static class SubSimpleMessageHandler extends SimpleMessageHandler {
    }


    private abstract static class GenericMessageHandler<T>
            implements MessageHandler.Basic<T> {
    }


    private static class GenericSubMessageHandler
            extends GenericMessageHandler<String>{

        @Override
        public void onMessage(String message) {
            // NO-OP
        }
    }


    private static interface Foo<T> {
        void doSomething(T thing);
    }


    private abstract static class GenericMultipleMessageHandler<A,B>
            implements MessageHandler.Basic<A>, Foo<B> {
    }


    private abstract static class GenericMultipleSubMessageHandler<X,Y>
            extends GenericMultipleMessageHandler<X,Y> {
    }


    private static class GenericMultipleSubSubMessageHandler
            extends GenericMultipleSubMessageHandler<String,Boolean> {

        @Override
        public void onMessage(String message) {
            // NO-OP
        }

        @Override
        public void doSomething(Boolean thing) {
            // NO-OP
        }
    }


    private abstract static class GenericMultipleSubSwapMessageHandler<Y,X>
            extends GenericMultipleMessageHandler<X,Y> {
    }


    private static class GenericMultipleSubSubSwapMessageHandler
            extends GenericMultipleSubSwapMessageHandler<Boolean,String> {

        @Override
        public void onMessage(String message) {
            // NO-OP
        }

        @Override
        public void doSomething(Boolean thing) {
            // NO-OP
        }
    }


    private static class SimpleEncoder
            implements Encoder.Text<String> {

        @Override
        public String encode(String object) throws EncodeException {
            return null;
        }
    }


    private static class SubSimpleEncoder extends SimpleEncoder {
    }


    private abstract static class GenericEncoder<T>
            implements Encoder.Text<T> {
    }


    private static class GenericSubEncoder
            extends GenericEncoder<String>{

        @Override
        public String encode(String object) throws EncodeException {
            return null;
        }

    }


    private abstract static class GenericMultipleEncoder<A,B>
            implements Encoder.Text<A>, Foo<B> {
    }


    private abstract static class GenericMultipleSubEncoder<X,Y>
            extends GenericMultipleEncoder<X,Y> {
    }


    private static class GenericMultipleSubSubEncoder
            extends GenericMultipleSubEncoder<String,Boolean> {

        @Override
        public String encode(String object) throws EncodeException {
            return null;
        }

        @Override
        public void doSomething(Boolean thing) {
            // NO-OP
        }

    }


    private abstract static class GenericMultipleSubSwapEncoder<Y,X>
            extends GenericMultipleEncoder<X,Y> {
    }


    private static class GenericMultipleSubSubSwapEncoder
            extends GenericMultipleSubSwapEncoder<Boolean,String> {

        @Override
        public String encode(String object) throws EncodeException {
            return null;
        }

        @Override
        public void doSomething(Boolean thing) {
            // NO-OP
        }
    }
}