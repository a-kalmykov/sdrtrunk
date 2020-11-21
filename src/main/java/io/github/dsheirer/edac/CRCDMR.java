/*******************************************************************************
 * sdr-trunk
 * Copyright (C) 2014-2018 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by  the Free Software Foundation, either version 3 of the License, or  (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied
 * warranty of  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License  along with this program.
 * If not, see <http://www.gnu.org/licenses/>
 *
 ******************************************************************************/
package io.github.dsheirer.edac;

import io.github.dsheirer.bits.BinaryMessage;
import io.github.dsheirer.bits.CorrectedBinaryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DMR CRC check/correction methods
 */
public class CRCDMR
{
    private final static Logger mLog = LoggerFactory.getLogger(CRCDMR.class);

    /**
     * CRC-CCITT 16-bit checksums for a message length of 80 bits plus 16
     * additional checksums representing CRC checksum bit errors
     *
     * Generated by:
     * CRCUtil.generate( 80, 16, 0x11021l, 0xFFFFl, true );
     */
    public static final int[] CCITT_80_CHECKSUMS = new int[]
        {
            0xE434, 0x721A, 0x390D, 0x9496, 0x4A4B, 0xAD35, 0xDE8A, 0x6F45, 0xBFB2,
            0x5FD9, 0xA7FC, 0x53FE, 0x29FF, 0x9CEF, 0xC667, 0xEB23, 0xFD81, 0xF6D0,
            0x7B68, 0x3DB4, 0x1EDA, 0xF6D, 0x8FA6, 0x47D3, 0xABF9, 0xDDEC, 0x6EF6,
            0x377B, 0x93AD, 0xC1C6, 0x60E3, 0xB861, 0xD420, 0x6A10, 0x3508, 0x1A84,
            0xD42, 0x6A1, 0x8B40, 0x45A0, 0x22D0, 0x1168, 0x8B4, 0x45A, 0x22D, 0x8906,
            0x4483, 0xAA51, 0xDD38, 0x6E9C, 0x374E, 0x1BA7, 0x85C3, 0xCAF1, 0xED68,
            0x76B4, 0x3B5A, 0x1DAD, 0x86C6, 0x4363, 0xA9A1, 0xDCC0, 0x6E60, 0x3730,
            0x1B98, 0xDCC, 0x6E6, 0x373, 0x89A9, 0xCCC4, 0x6662, 0x3331, 0x9188,
            0x48C4, 0x2462, 0x1231, 0x8108, 0x4084, 0x2042, 0x1021, 0x1, 0x2, 0x4, 0x8,
            0x10, 0x20, 0x40, 0x80, 0x100, 0x200, 0x400, 0x800, 0x1000, 0x2000, 0x4000,
            0x8000,
        };

    /**
     * Confirmed Packet Data Unit CRC-9 checksums, generated by:
     *
     * long[] table = CRCUtil.generate( 135, 9, 0x259l, 0x1FF, false, Parity.NONE );
     */
    public static final int[] CRC9_CHECKSUMS = new int[]
        {
            0x1E7, 0x1F3, 0x1F9, 0x1FC, 0x0D2, 0x045, 0x122, 0x0BD, 0x15E, 0x083,
            0x141, 0x1A0, 0x0FC, 0x052, 0x005, 0x102, 0x0AD, 0x156, 0x087, 0x143,
            0x1A1, 0x1D0, 0x0C4, 0x04E, 0x00B, 0x105, 0x182, 0x0ED, 0x176, 0x097,
            0x14B, 0x1A5, 0x1D2, 0x0C5, 0x162, 0x09D, 0x14E, 0x08B, 0x145, 0x1A2,
            0x0FD, 0x17E, 0x093, 0x149, 0x1A4, 0x0FE, 0x053, 0x129, 0x194, 0x0E6,
            0x05F, 0x12F, 0x197, 0x1CB, 0x1E5, 0x1F2, 0x0D5, 0x16A, 0x099, 0x14C,
            0x08A, 0x069, 0x134, 0x0B6, 0x077, 0x13B, 0x19D, 0x1CE, 0x0CB, 0x165,
            0x1B2, 0x0F5, 0x17A, 0x091, 0x148, 0x088, 0x068, 0x018, 0x020, 0x03C,
            0x032, 0x035, 0x11A, 0x0A1, 0x150, 0x084, 0x06E, 0x01B, 0x10D, 0x186,
            0x0EF, 0x177, 0x1BB, 0x1DD, 0x1EE, 0x0DB, 0x16D, 0x1B6, 0x0F7, 0x17B,
            0x1BD, 0x1DE, 0x0C3, 0x161, 0x1B0, 0x0F4, 0x056, 0x007, 0x103, 0x181,
            0x1C0, 0x0CC, 0x04A, 0x009, 0x104, 0x0AE, 0x07B, 0x13D, 0x19E, 0x0E3,
            0x171, 0x1B8, 0x0F0, 0x054, 0x006, 0x02F, 0x117, 0x18B, 0x1C5, 0x1E2,
            0x0DD, 0x16E, 0x09B, 0x14D, 0x1A6
        };


    /**
     * Error detection and correction of single-bit errors for CCITT 16-bit CRC protected 80-bit messages.
     */
    public static BinaryMessage correctCCITT80(BinaryMessage message, int messageStart, int crcStart)
    {
        int calculated = 0; //Starting value

        /* Iterate the set bits and XOR running checksum with lookup value */
        for(int i = message.nextSetBit(messageStart);
            i >= messageStart && i < crcStart;
            i = message.nextSetBit(i + 1))
        {
            calculated ^= CCITT_80_CHECKSUMS[i - messageStart];
        }

        int checksum = getIntChecksum(message, crcStart, 16);

        int residual = calculated ^ checksum;

        if(residual == 0 || residual == 0xFFFF)
        {
            message.setCRC(CRC.PASSED);

            return message;
        }
        else
        {
            int errorLocation = getBitError(residual, CCITT_80_CHECKSUMS);

            if(errorLocation >= 0)
            {
                message.flip(errorLocation + messageStart);

                message.setCRC(CRC.CORRECTED);

                return message;
            }
        }

        message.setCRC(CRC.FAILED_CRC);

        return message;
    }

    /**
     * Calculates the CRC checksum for the specified message
     * @param message to check
     * @param start of the message
     * @param length of the message (assumes the CRC checksum follows)
     * @param checksums to use in calculating the message checksum
     * @return calculated checksum
     */
    public static int calculate(BinaryMessage message, int start, int length, long[] checksums)
    {
        int calculated = 0;

        /* Iterate the set bits and XOR running checksum with lookup value */
        for(int i = message.nextSetBit(start); i >= start && i < length; i = message.nextSetBit(i + 1))
        {
            calculated ^= checksums[i - start];
        }

        return calculated;
    }

    /**
     * Error detection and correction of single-bit errors for CCITT 16-bit CRC protected 80-bit messages.
     */
    public static int correctCCITT80(CorrectedBinaryMessage message, int messageStart, int crcStart, int mask)
    {
        int calculated = mask; //Starting value

        /* Iterate the set bits and XOR running checksum with lookup value */
        for(int i = message.nextSetBit(messageStart); i >= messageStart && i < crcStart; i = message.nextSetBit(i + 1))
        {
            calculated ^= CCITT_80_CHECKSUMS[i - messageStart];
        }

        int checksum = getIntChecksum(message, crcStart, 16);

        int residual = calculated ^ checksum;

        if(residual == 0 || residual == 0xFFFF)
        {
            return 0;
        }
        else
        {
            int errorLocation = getBitError(residual, CCITT_80_CHECKSUMS);

            if(errorLocation >= 0)
            {
                message.flip(errorLocation + messageStart);
                message.incrementCorrectedBitCount(1);
                return 1;
            }
        }

        //Message has at least 2 bit errors - ie uncorrectable
        message.incrementCorrectedBitCount(2);

        return 2;
    }

    /**
     * Error detection for CRC-9 protected Confirmed Packet Data blocks.  These
     * data blocks have a slightly complicated structure because the checksum
     * is located between bits 7-15, within a 144 bit block.  The checksums
     * were generated assuming that the message is contiguous from 0 - 134 bits.
     * No data correction is performed.
     */
    public static CRC checkCRC9(BinaryMessage message, int messageStart)
    {
        int calculated = 0x0; //Initial fill of all ones

        /* Iterate the set bits and XOR running checksum with lookup value */
        for(int i = message.nextSetBit(messageStart);
            i >= messageStart && i < messageStart + 144;
            i = message.nextSetBit(i + 1))
        {
            /* message bits before the CRC */
            if(i < (messageStart + 7))
            {
                calculated ^= CRC9_CHECKSUMS[i - messageStart];
            }
            /* message bits after the CRC */
            else if(i > (messageStart + 15))
            {
                calculated ^= CRC9_CHECKSUMS[i - messageStart - 9];
            }
        }

        int checksum = message.getInt(messageStart + 7, messageStart + 15);

        int residual = calculated ^ checksum;

        if(residual == 0 || residual == 0x1FF)
        {
            return CRC.PASSED;
        }

        return CRC.FAILED_CRC;
    }

    /**
     * Calculates the value of the message checksum as a long
     */
    public static long getLongChecksum(BinaryMessage message,
                                       int crcStart, int crcLength)
    {
        return message.getLong(crcStart, crcStart + crcLength - 1);
    }

    /**
     * Calculates the value of the message checksum as an integer
     */
    public static int getIntChecksum(BinaryMessage message,
                                     int crcStart, int crcLength)
    {
        return message.getInt(crcStart, crcStart + crcLength - 1);
    }

    /**
     * Identifies any single bit error position that matches the checksum error.
     */
    public static int getBitError(long checksumError, long[] checksums)
    {
        for(int x = 0; x < checksums.length; x++)
        {
            if(checksums[x] == checksumError)
            {
                return x;
            }
        }

        return -1;
    }

    /**
     * Identifies any single bit error position that matches the checksum error.
     */
    public static int getBitError(int checksumError, int[] checksums)
    {
        for(int x = 0; x < checksums.length; x++)
        {
            if(checksums[x] == checksumError)
            {
                return x;
            }
        }

        return -1;
    }

    public static int crc8(BinaryMessage bits, int len) {
        int crc=0;
        final int K = 8;
        final boolean[] poly = new boolean[]{true,false,false,false,false,false,true,true,true}; // crc8 poly
        boolean[] buf = new boolean[256];
        if (len+K > 256) {
            return 0;
        }
        for (int i=0; i<len; i++){
            buf[i] = bits.get(i);
        }
        for (int i=0; i<len; i++)
            if (buf[i])
                for (int j=0; j<K+1; j++)
                    buf[i+j] ^= poly[j];
        for (int i=0; i<K; i++){
            crc = (crc << 1) + (buf[len + i] ? 1:0);
        }
        return crc;
    }
}
