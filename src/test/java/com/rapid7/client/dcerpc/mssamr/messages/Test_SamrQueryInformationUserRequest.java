/*
 * Copyright 2017, Rapid7, Inc.
 *
 * License: BSD-3-clause
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 *
 */

package com.rapid7.client.dcerpc.mssamr.messages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.util.encoders.Hex;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.rapid7.client.dcerpc.io.PacketOutput;
import com.rapid7.client.dcerpc.mssamr.objects.UserHandle;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class Test_SamrQueryInformationUserRequest {

    @DataProvider
    public Object[][] data_requests() {
        UserHandle handle = new UserHandle();
        handle.setBytes(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
        return new Object[][] {
                {new SamrQueryInformationUserRequest.UserAllInformation(handle)}
        };
    }

    @Test(dataProvider = "data_requests")
    public void test_getOpNum(SamrQueryInformationUserRequest request) {
        assertEquals(request.getOpNum(), SamrQueryInformationUserRequest.OP_NUM);
    }

    @Test(dataProvider = "data_requests")
    public void test_getResponseObject(SamrQueryInformationUserRequest request) {
        assertNotNull(request.getResponseObject());
    }

    @Test(dataProvider = "data_requests")
    public void test_marshall(SamrQueryInformationUserRequest request) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PacketOutput out = new PacketOutput(bout);
        request.marshal(out);

        assertEquals(Hex.toHexString(bout.toByteArray()), "0102030405060708090a0b0c0d0e0f10111213141500");
    }
}
