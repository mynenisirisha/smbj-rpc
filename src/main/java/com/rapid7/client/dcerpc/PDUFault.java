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

package com.rapid7.client.dcerpc;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="http://pubs.opengroup.org/onlinepubs/9629399/apdxe.htm">fault_status Parameter</a>
 * <blockquote><pre>Both reject and connection-oriented fault PDUs contain a 32-bit field that indicates a server's reason for rejecting an RPC call request.
 * This field is encoded as the body data of the reject PDU and as the status field of the connection-oriented fault PDU header.</pre></blockquote>
 *
 * Reference: <a href="https://github.com/boundary/wireshark/blob/73a610b4e74102eea706985ae454a83582eae0b3/epan/dissectors/packet-dcerpc.c#L265">Wireshark</a>
 *
 * Since both rejection and fault codes are unique within the same 32bit integer field,
 * they are represented here in the same enumeration.
 */
public enum PDUFault {
    UNKNOWN(0x00000000),
    // DCERPC
    NCA_S_FAULT_OTHER(0x00000001),
    NCA_S_FAULT_ACCESS_DENIED(0x00000005),
    NCA_S_FAULT_NDR(0x000006F7),
    NCA_S_FAULT_CANT_PERFORM(0x000006D8),
    NCA_S_FAULT_INT_DIV_BY_ZERO(0x1C000001),
    NCA_S_FAULT_ADDR_ERROR(0x1C000002),
    NCA_S_FAULT_FP_DIV_ZERO(0x1C000003),
    NCA_S_FAULT_FP_UNDERFLOW(0x1C000004),
    NCA_S_FAULT_FP_OVERFLOW(0x1C000005),
    NCA_S_FAULT_INVALID_TAG(0x1C000006),
    NCA_S_FAULT_INVALID_BOUND(0x1C000007),
    NCA_RPC_VERSION_MISMATCH(0x1C000008),
    NCA_UNSPEC_REJECT(0x1C000009),
    NCA_S_BAD_ACTID(0x1C00000A),
    NCA_WHO_ARE_YOU_FAILED(0x1C00000B),
    NCA_MANAGER_NOT_ENTERED(0x1C00000C),
    NCA_S_FAULT_CANCEL(0x1C00000D),
    NCA_S_FAULT_ILL_INST(0x1C00000E),
    NCA_S_FAULT_FP_ERROR(0x1C00000F),
    NCA_S_FAULT_INT_OVERFLOW(0x1C000010),
    NCA_S_FAULT_PIPE_EMPTY(0x1C000014),
    NCA_S_FAULT_PIPE_CLOSED(0x1C000015),
    NCA_S_FAULT_PIPE_ORDER(0x1C000016),
    NCA_S_FAULT_PIPE_DISCIPLINE(0x1C000017),
    NCA_S_FAULT_PIPE_COMM_ERROR(0x1C000018),
    NCA_S_FAULT_PIPE_MEMORY(0x1C000019),
    NCA_S_FAULT_CONTEXT_MISMATCH(0x1C00001A),
    NCA_S_FAULT_REMOTE_NO_MEMORY(0x1C00001B),
    NCA_INVALID_PRES_CONTEXT_ID(0x1C00001C),
    NCA_UNSUPPORTED_AUTHN_LEVEL(0x1C00001D),
    NCA_INVALID_CHECKSUM(0x1C00001F),
    NCA_INVALID_CRC(0x1C000020),
    NCS_S_FAULT_USER_DEFINED(0x1C000021),
    NCA_S_FAULT_TX_OPEN_FAILED(0x1C000022),
    NCA_S_FAULT_CODESET_CONV_ERROR(0x1C000023),
    NCA_S_FAULT_OBJECT_NOT_FOUND(0x1C000024),
    NCA_S_FAULT_NO_CLIENT_STUB(0x1C000025),
    NCA_OP_RNG_ERROR(0x1C010002),
    NCA_UNK_IF(0x1C010003),
    NCA_WRONG_BOOT_TIME(0x1C010006),
    NCA_S_YOU_CRASHED(0x1C010009),
    NCA_PROTO_ERROR(0x1C01000B),
    NCA_OUT_ARGS_TOO_BIG(0x1C010013),
    NCA_SERVER_TOO_BUSY(0x1C010014),
    NCA_UNSUPPORTED_TYPE(0x1C010017),
    // Microsoft specific codes
    E_NOTIMPL(0x80004001),
    E_POINTER(0x80004003),
    E_AOBRT(0x80004004),
    E_UNEXPECTED(0x8000FFFF),
    RPC_E_SERVERFAULT(0x80010105),
    RPC_E_DISCONNECTED(0x80010108),
    RPC_E_INVALID_IPID(0x80010113),
    RPC_E_TIMEOUT(0x8001011F),
    DISP_E_MEMBERNOTFOUND(0x80020003),
    DISP_E_UNKNOWNNAME(0x80020006),
    DISP_E_BADPARAMCOUNT(0x8002000E),
    CBA_E_MALFORMED(0x8004CB00),
    CBA_E_UNKNOWNOBJECT(0x8004CB01),
    CBA_E_INVALIDID(0x8004CB05),
    CBA_E_INVALIDCOOKIE(0x8004CB09),
    CBA_E_QOSTYPEUNSUPPORTED(0x8004CB0B),
    CBA_E_QOSVALUEUNSUPPORTED(0x8004CB0C),
    CBA_E_NOTAPPLICABLE(0x8004CB0F),
    CBA_E_LIMITVIOLATION(0x8004CB12),
    CBA_E_QOSTYPENOTAPPLICABLE(0x8004CB13),
    CBA_E_OUTOFPARTNERACCOS(0x8004CB18),
    CBA_E_FLAGUNSUPPORTED(0x8004CB1C),
    CBA_E_FRAMECOUNTUNSUPPORTED(0x8004CB23),
    CBA_E_MODECHANGE(0x8004CB25),
    E_OUTOFMEMORY(0x8007000E),
    E_INVALIDARG(0x80070057),
    RPC_S_PROCNUM_OUT_OF_RANGE(0x800706D1),
    OR_INVALID_OXID(0x80070776);

    private final int value;

    PDUFault(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    private static final Map<Integer, PDUFault> VALUE_MAP = new HashMap<>();
    static {
        for (PDUFault rpcFault : PDUFault.values()) {
            VALUE_MAP.put(rpcFault.getValue(), rpcFault);
        }
    }

    public static PDUFault fromValue(final int value) {
        final PDUFault ret =  VALUE_MAP.get(value);
        if (ret == null) {
            return PDUFault.UNKNOWN;
        }
        return ret;
    }
}
