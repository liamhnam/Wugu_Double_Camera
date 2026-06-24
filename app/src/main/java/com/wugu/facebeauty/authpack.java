package com.wugu.facebeauty;

import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.security.MessageDigest;
import jp.p036co.dnp.photoprintlib.DNPPhotoPrint;
import kotlin.UByte;

public class authpack {
    public static byte[] m1184A() {
        return $d2j$hex$3a27aaff$decode_B("18ad910ed08609a968b9088099ce58843d8d79199b5fa65bc751e054034e554269f0880b14fe76169c97e5df5ed7d7fff2496536901dd815829aafd7e29ec28d0342a53c652efdcb35ddab91d412967d0c1dabdeb62552b7f37dc152a70a97897a13c4fd8c58ec7dc9f01d567687804a0e2a6b1378bbeef094b01502f7cc450578614d3736813127f2697435dc77aa959431a96a72797fa996c4d315142eaed96cea0122f8d71c5cbed84f900d9100ae9a9db55b196c9047873a731aaee7b04b25217d87fac2479b54a1c4f9c60eae5cc583f1dd79a4dac2b27f68abd112238015e8488594ee30bc7ef2244aebb5df1dc909885f09d4c49c35f9450280bb89dc28e6202bc61ab0830992a0d3156eb51787788bc563cf7a3b60890a556a5968e5d99ea5dfcb60e1f7b85ade96b9776384baf4d8625b787144f9f05231452f3caac697f2185947e0ebd81bfd3376bb7861a841b079940dcf9b4e8c23b298a65458b50f3264ca5ba1f987743bd45ed4746186afa130aafc523614152d6d953e761df58dd3111d9d05f1051d3ced6a479f46dba1e39cde5f714d75cd140b57f8eec83aa8b99178f4f67ed6e44029923ee105898dd496590de5a5478cc2cf938f7e62ea59f8434c3ce88200cc87b45eda9ccde265c98a27512c3ef5ace2d7467ecefca1489176dd7c767acf6c4a3b0a8c9572d0e1df4e352bbe4b2ed5b8273146ac6c169dc83d283404d0273045547b9a86e3e60e9233a2b568c4b795805fdf25a66f8115853996bfe379bbce710b3a1128a6dbb27de3d4df1c8d1b39be342c4d61c08cd01ddb495e04d263e2a84619de1aec75a6a132f7cb9d478522deda03214f22ccb9b7a67cc7880765d2da0abc42f96a48650b759bc14b6b3a312c1894b8bb6cd6173171812583ff40717ae17ab9b788dcac221da9e5449471ad77f34fe84170ebf5a3e3f34804a8c530d68479f9f5bc6c3c7e6992495dcf167596de7cc67b8e9f9423621ee1de0d36206f594beeb9fc9bf010cb3a98d179aaa9633062e1b0dafcd2512ae4822ed6062e1550a42fb1cfdecb1a086ddfd3fdcaaae012122253f0f8346012079ab85a445c91e0ae195d46fdbdf157f256eff37c52373e497615bebda2a32e6e7299123527c65dc3e74e07de0f85cada6e574f845da059398b8a23e06a7c3cffa2d2e7257eab46cd9f94e9876e7f657dfcd5e0a6b549ca0eca52c900fd276691169ed179b08f3a7afaa7882eec48377222e3d3ef2d1f93bcf9ff3febdbaad2892e93c98e806c8221310e6352196095981e19a7a7fcac1e78d22417d292e9bc45a1dc10ba239462fcff0b5c89d8a976f54f7b81e086bbcf3662cfcf086e1b925cd38e63d537fc2fe69f69e7acbc2bc831347910bab0cdcd4a16ed71528ea8bbddb0ea67188c968af0f084d2dc844927dccf7b884bc8d632324febfbc633ca82090664b4f8827476d85e3c63fffc0bdd82cdc1484137d3a51719ac9c34bcf45c4e95e3581dc6ca68c876a8a941099f3f2fb19fa9230c4e1d8e7329b285b99214a817bcfd903fd32512f6df062cbc6f8f786765fef5d96b7988dfebbe1c8bc0037791f89266cb420631a897b9c5803c037de91d43f7828b71b9e6f997a6b81badca0bca02e1bb81d21c3f7420730f1328f40feb644fc31190e61f850882a0371194be48f913c2acf146508c65e587a8e8a097f5bccc1e03db517b517a7c37d3558661aeed577936b5c3b9f7903ef689");
    }

    public static int sha1_32_(byte[] bArr) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(bArr);
            return ((bArrDigest[0] & UByte.MAX_VALUE) << 24) + ((bArrDigest[1] & UByte.MAX_VALUE) << 16) + ((bArrDigest[2] & UByte.MAX_VALUE) << 8) + ((bArrDigest[3] & UByte.MAX_VALUE) << 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    private static long[] $d2j$hex$3a27aaff$decode_J(String str) {
        byte[] bArr$d2j$hex$3a27aaff$decode_B = $d2j$hex$3a27aaff$decode_B(str);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr$d2j$hex$3a27aaff$decode_B);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        LongBuffer longBufferAsLongBuffer = byteBufferWrap.asLongBuffer();
        long[] jArr = new long[bArr$d2j$hex$3a27aaff$decode_B.length / 8];
        longBufferAsLongBuffer.get(jArr);
        return jArr;
    }

    private static int[] $d2j$hex$3a27aaff$decode_I(String str) {
        byte[] bArr$d2j$hex$3a27aaff$decode_B = $d2j$hex$3a27aaff$decode_B(str);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr$d2j$hex$3a27aaff$decode_B);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        IntBuffer intBufferAsIntBuffer = byteBufferWrap.asIntBuffer();
        int[] iArr = new int[bArr$d2j$hex$3a27aaff$decode_B.length / 4];
        intBufferAsIntBuffer.get(iArr);
        return iArr;
    }

    private static short[] $d2j$hex$3a27aaff$decode_S(String str) {
        byte[] bArr$d2j$hex$3a27aaff$decode_B = $d2j$hex$3a27aaff$decode_B(str);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr$d2j$hex$3a27aaff$decode_B);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        ShortBuffer shortBufferAsShortBuffer = byteBufferWrap.asShortBuffer();
        short[] sArr = new short[bArr$d2j$hex$3a27aaff$decode_B.length / 2];
        shortBufferAsShortBuffer.get(sArr);
        return sArr;
    }

    private static byte[] $d2j$hex$3a27aaff$decode_B(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = i5 * 2;
            char c = charArray[i6];
            char c2 = charArray[i6 + 1];
            if (c < '0' || c > '9') {
                if (c >= 'a' && c <= 'f') {
                    i = c - 'a';
                } else {
                    if (c < 'A' || c > 'F') {
                        throw new RuntimeException();
                    }
                    i = c - 'A';
                }
                i2 = i + 10;
            } else {
                i2 = c - '0';
            }
            if (c2 < '0' || c2 > '9') {
                if (c2 >= 'a' && c2 <= 'f') {
                    i3 = c2 - 'a';
                } else {
                    if (c2 < 'A' || c2 > 'F') {
                        throw new RuntimeException();
                    }
                    i3 = c2 - 'A';
                }
                i4 = i3 + 10;
            } else {
                i4 = c2 - '0';
            }
            bArr[i5] = (byte) (i4 | (i2 << 4));
        }
        return bArr;
    }

    public static int sha1_32(byte[] bArr) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(bArr);
            return ((bArrDigest[0] & UByte.MAX_VALUE) << 24) + ((bArrDigest[1] & UByte.MAX_VALUE) << 16) + ((bArrDigest[2] & UByte.MAX_VALUE) << 8) + ((bArrDigest[3] & UByte.MAX_VALUE) << 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static byte[] m1185A2() {
        int i;
        int i2;
        int i3;
        int i4;
        byte[] bArr = new byte[1275];
        for (int i5 = -86; i5 < -60; i5++) {
            bArr[0] = (byte) i5;
            if (sha1_32(bArr) == 3220484) {
                break;
            }
        }
        for (int i6 = 6; i6 < 20; i6++) {
            bArr[1] = (byte) i6;
            if (sha1_32(bArr) == 1120522757) {
                break;
            }
        }
        for (int i7 = 35; i7 < 57; i7++) {
            bArr[2] = (byte) i7;
            if (sha1_32(bArr) == 1648550627) {
                break;
            }
        }
        for (int i8 = 19; i8 < 39; i8++) {
            bArr[3] = (byte) i8;
            if (sha1_32(bArr) == -1511372474) {
                break;
            }
        }
        for (int i9 = -55; i9 < -47; i9++) {
            bArr[4] = (byte) i9;
            if (sha1_32(bArr) == 1467754398) {
                break;
            }
        }
        for (int i10 = -86; i10 < -77; i10++) {
            bArr[5] = (byte) i10;
            if (sha1_32(bArr) == -1235080054) {
                break;
            }
        }
        for (int i11 = -108; i11 < -91; i11++) {
            bArr[6] = (byte) i11;
            if (sha1_32(bArr) == -716416118) {
                break;
            }
        }
        for (int i12 = -115; i12 < -94; i12++) {
            bArr[7] = (byte) i12;
            if (sha1_32(bArr) == -492002245) {
                break;
            }
        }
        for (int i13 = -22; i13 < -14; i13++) {
            bArr[8] = (byte) i13;
            if (sha1_32(bArr) == -355840201) {
                break;
            }
        }
        for (int i14 = 41; i14 < 48; i14++) {
            bArr[9] = (byte) i14;
            if (sha1_32(bArr) == 1953751339) {
                break;
            }
        }
        for (int i15 = -94; i15 < -88; i15++) {
            bArr[10] = (byte) i15;
            if (sha1_32(bArr) == -919755478) {
                break;
            }
        }
        for (int i16 = 32; i16 < 53; i16++) {
            bArr[11] = (byte) i16;
            if (sha1_32(bArr) == 881160884) {
                break;
            }
        }
        for (int i17 = 91; i17 < 94; i17++) {
            bArr[12] = (byte) i17;
            if (sha1_32(bArr) == 55177073) {
                break;
            }
        }
        for (int i18 = 84; i18 < 102; i18++) {
            bArr[13] = (byte) i18;
            if (sha1_32(bArr) == 162236922) {
                break;
            }
        }
        int i19 = 14;
        while (true) {
            if (i19 >= 27) {
                break;
            }
            bArr[14] = (byte) i19;
            if (sha1_32(bArr) == 657070476) {
                break;
            }
            i19++;
        }
        for (int i20 = -66; i20 < -56; i20++) {
            bArr[15] = (byte) i20;
            if (sha1_32(bArr) == -2084563922) {
                break;
            }
        }
        for (int i21 = -54; i21 < -29; i21++) {
            bArr[16] = (byte) i21;
            if (sha1_32(bArr) == -1484556248) {
                break;
            }
        }
        for (int i22 = 82; i22 < 90; i22++) {
            bArr[17] = (byte) i22;
            if (sha1_32(bArr) == 317300478) {
                break;
            }
        }
        for (int i23 = -118; i23 < -93; i23++) {
            bArr[18] = (byte) i23;
            if (sha1_32(bArr) == 1916181031) {
                break;
            }
        }
        for (int i24 = -128; i24 < -115; i24++) {
            bArr[19] = (byte) i24;
            if (sha1_32(bArr) == -278851725) {
                break;
            }
        }
        for (int i25 = -102; i25 < -81; i25++) {
            bArr[20] = (byte) i25;
            if (sha1_32(bArr) == -994134589) {
                break;
            }
        }
        for (int i26 = -83; i26 < -62; i26++) {
            bArr[21] = (byte) i26;
            if (sha1_32(bArr) == 1691803809) {
                break;
            }
        }
        for (int i27 = 59; i27 < 61; i27++) {
            bArr[22] = (byte) i27;
            if (sha1_32(bArr) == -1933665717) {
                break;
            }
        }
        for (int i28 = -128; i28 < -111; i28++) {
            bArr[23] = (byte) i28;
            if (sha1_32(bArr) == -646627832) {
                break;
            }
        }
        for (int i29 = -110; i29 < -94; i29++) {
            bArr[24] = (byte) i29;
            if (sha1_32(bArr) == -1289174072) {
                break;
            }
        }
        for (int i30 = -26; i30 < -3; i30++) {
            bArr[25] = (byte) i30;
            if (sha1_32(bArr) == 1045558542) {
                break;
            }
        }
        for (int i31 = -49; i31 < -23; i31++) {
            bArr[26] = (byte) i31;
            if (sha1_32(bArr) == 958695056) {
                break;
            }
        }
        for (int i32 = -32; i32 < -19; i32++) {
            bArr[27] = (byte) i32;
            if (sha1_32(bArr) == 1216363227) {
                break;
            }
        }
        for (int i33 = -11; i33 < 6; i33++) {
            bArr[28] = (byte) i33;
            if (sha1_32(bArr) == 938821214) {
                break;
            }
        }
        for (int i34 = 87; i34 < 108; i34++) {
            bArr[29] = (byte) i34;
            if (sha1_32(bArr) == 470267365) {
                break;
            }
        }
        for (int i35 = 34; i35 < 55; i35++) {
            bArr[30] = (byte) i35;
            if (sha1_32(bArr) == 1131001541) {
                break;
            }
        }
        for (int i36 = -29; i36 < -27; i36++) {
            bArr[31] = (byte) i36;
            if (sha1_32(bArr) == -932643706) {
                break;
            }
        }
        for (int i37 = 124; i37 < 128; i37++) {
            bArr[32] = (byte) i37;
            if (sha1_32(bArr) == 1868178222) {
                break;
            }
        }
        for (int i38 = 68; i38 < 85; i38++) {
            bArr[33] = (byte) i38;
            if (sha1_32(bArr) == -416821230) {
                break;
            }
        }
        for (int i39 = 104; i39 < 120; i39++) {
            bArr[34] = (byte) i39;
            if (sha1_32(bArr) == 1281778885) {
                break;
            }
        }
        for (int i40 = 22; i40 < 34; i40++) {
            bArr[35] = (byte) i40;
            if (sha1_32(bArr) == 1888412602) {
                break;
            }
        }
        for (int i41 = -51; i41 < -45; i41++) {
            bArr[36] = (byte) i41;
            if (sha1_32(bArr) == 1080886032) {
                break;
            }
        }
        for (int i42 = -11; i42 < 6; i42++) {
            bArr[37] = (byte) i42;
            if (sha1_32(bArr) == 870390784) {
                break;
            }
        }
        for (int i43 = 23; i43 < 43; i43++) {
            bArr[38] = (byte) i43;
            if (sha1_32(bArr) == 899004012) {
                break;
            }
        }
        for (int i44 = 31; i44 < 55; i44++) {
            bArr[39] = (byte) i44;
            if (sha1_32(bArr) == 668112710) {
                break;
            }
        }
        for (int i45 = -120; i45 < -98; i45++) {
            bArr[40] = (byte) i45;
            if (sha1_32(bArr) == -943311648) {
                break;
            }
        }
        for (int i46 = -97; i46 < -91; i46++) {
            bArr[41] = (byte) i46;
            if (sha1_32(bArr) == -656024301) {
                break;
            }
        }
        for (int i47 = 0; i47 < 9; i47++) {
            bArr[42] = (byte) i47;
            if (sha1_32(bArr) == -1642237434) {
                break;
            }
        }
        for (int i48 = -24; i48 < -8; i48++) {
            bArr[43] = (byte) i48;
            if (sha1_32(bArr) == -538116501) {
                break;
            }
        }
        for (int i49 = 65; i49 < 87; i49++) {
            bArr[44] = (byte) i49;
            if (sha1_32(bArr) == -1620887380) {
                break;
            }
        }
        for (int i50 = 54; i50 < 64; i50++) {
            bArr[45] = (byte) i50;
            if (sha1_32(bArr) == -1262225579) {
                break;
            }
        }
        for (int i51 = -121; i51 < -103; i51++) {
            bArr[46] = (byte) i51;
            if (sha1_32(bArr) == -474527356) {
                break;
            }
        }
        for (int i52 = -102; i52 < -79; i52++) {
            bArr[47] = (byte) i52;
            if (sha1_32(bArr) == -1285676145) {
                break;
            }
        }
        for (int i53 = -82; i53 < -61; i53++) {
            bArr[48] = (byte) i53;
            if (sha1_32(bArr) == -555791470) {
                break;
            }
        }
        bArr[49] = (byte) 61;
        sha1_32(bArr);
        for (int i54 = -15; i54 < 2; i54++) {
            bArr[50] = (byte) i54;
            if (sha1_32(bArr) == -1343880456) {
                break;
            }
        }
        for (int i55 = -39; i55 < -25; i55++) {
            bArr[51] = (byte) i55;
            if (sha1_32(bArr) == 1114009998) {
                break;
            }
        }
        for (int i56 = 8; i56 < 36; i56++) {
            bArr[52] = (byte) i56;
            if (sha1_32(bArr) == -1807734670) {
                break;
            }
        }
        for (int i57 = 21; i57 < 33; i57++) {
            bArr[53] = (byte) i57;
            if (sha1_32(bArr) == 1078921317) {
                break;
            }
        }
        for (int i58 = -125; i58 < -117; i58++) {
            bArr[54] = (byte) i58;
            if (sha1_32(bArr) == 256313109) {
                break;
            }
        }
        for (int i59 = 28; i59 < 45; i59++) {
            bArr[55] = (byte) i59;
            if (sha1_32(bArr) == -1443402730) {
                break;
            }
        }
        for (int i60 = -62; i60 < -40; i60++) {
            bArr[56] = (byte) i60;
            if (sha1_32(bArr) == -2124580748) {
                break;
            }
        }
        int i61 = 95;
        while (true) {
            if (i61 >= 109) {
                break;
            }
            bArr[57] = (byte) i61;
            if (sha1_32(bArr) == 2092318952) {
                break;
            }
            i61++;
        }
        for (int i62 = -13; i62 < 2; i62++) {
            bArr[58] = (byte) i62;
            if (sha1_32(bArr) == -740937206) {
                break;
            }
        }
        for (int i63 = -57; i63 < -38; i63++) {
            bArr[59] = (byte) i63;
            if (sha1_32(bArr) == -963384902) {
                break;
            }
        }
        for (int i64 = -42; i64 < -19; i64++) {
            bArr[60] = (byte) i64;
            if (sha1_32(bArr) == -35954368) {
                break;
            }
        }
        for (int i65 = 117; i65 < 128; i65++) {
            bArr[61] = (byte) i65;
            if (sha1_32(bArr) == -1476718211) {
                break;
            }
        }
        for (int i66 = -128; i66 < -116; i66++) {
            bArr[62] = (byte) i66;
            if (sha1_32(bArr) == 1545893975) {
                break;
            }
        }
        int i67 = 78;
        while (true) {
            if (i67 >= 97) {
                break;
            }
            bArr[63] = (byte) i67;
            if (sha1_32(bArr) == 690356351) {
                break;
            }
            i67++;
        }
        for (int i68 = 120; i68 < 128; i68++) {
            bArr[64] = (byte) i68;
            if (sha1_32(bArr) == 1838611512) {
                break;
            }
        }
        for (int i69 = 73; i69 < 79; i69++) {
            bArr[65] = (byte) i69;
            if (sha1_32(bArr) == -1757410148) {
                break;
            }
        }
        int i70 = -78;
        while (true) {
            if (i70 >= -71) {
                break;
            }
            bArr[66] = (byte) i70;
            if (sha1_32(bArr) == -725646594) {
                break;
            }
            i70++;
        }
        for (int i71 = 55; i71 < 64; i71++) {
            bArr[67] = (byte) i71;
            if (sha1_32(bArr) == 1765649490) {
                break;
            }
        }
        for (int i72 = -58; i72 < -41; i72++) {
            bArr[68] = (byte) i72;
            if (sha1_32(bArr) == 1355350218) {
                break;
            }
        }
        for (int i73 = -124; i73 < -105; i73++) {
            bArr[69] = (byte) i73;
            if (sha1_32(bArr) == -1492230612) {
                break;
            }
        }
        for (int i74 = -40; i74 < -22; i74++) {
            bArr[70] = (byte) i74;
            if (sha1_32(bArr) == 1707639597) {
                break;
            }
        }
        for (int i75 = 37; i75 < 56; i75++) {
            bArr[71] = (byte) i75;
            if (sha1_32(bArr) == -913502108) {
                break;
            }
        }
        for (int i76 = -21; i76 < -9; i76++) {
            bArr[72] = (byte) i76;
            if (sha1_32(bArr) == -1804729489) {
                break;
            }
        }
        for (int i77 = 98; i77 < 127; i77++) {
            bArr[73] = (byte) i77;
            if (sha1_32(bArr) == 1564691492) {
                break;
            }
        }
        for (int i78 = -121; i78 < -105; i78++) {
            bArr[74] = (byte) i78;
            if (sha1_32(bArr) == -2137116688) {
                break;
            }
        }
        for (int i79 = -106; i79 < -88; i79++) {
            bArr[75] = (byte) i79;
            if (sha1_32(bArr) == -827912441) {
                break;
            }
        }
        for (int i80 = -105; i80 < -79; i80++) {
            bArr[76] = (byte) i80;
            if (sha1_32(bArr) == -1515323266) {
                break;
            }
        }
        for (int i81 = 84; i81 < 105; i81++) {
            bArr[77] = (byte) i81;
            if (sha1_32(bArr) == 1593101781) {
                break;
            }
        }
        for (int i82 = -28; i82 < -12; i82++) {
            bArr[78] = (byte) i82;
            if (sha1_32(bArr) == 1008923634) {
                break;
            }
        }
        for (int i83 = -56; i83 < -36; i83++) {
            bArr[79] = (byte) i83;
            if (sha1_32(bArr) == 917028911) {
                break;
            }
        }
        for (int i84 = -113; i84 < -87; i84++) {
            bArr[80] = (byte) i84;
            if (sha1_32(bArr) == -2044396911) {
                break;
            }
        }
        for (int i85 = 51; i85 < 71; i85++) {
            bArr[81] = (byte) i85;
            if (sha1_32(bArr) == -735990027) {
                break;
            }
        }
        for (int i86 = -42; i86 < -33; i86++) {
            bArr[82] = (byte) i86;
            if (sha1_32(bArr) == -1113223839) {
                break;
            }
        }
        for (int i87 = -2; i87 < 13; i87++) {
            bArr[83] = (byte) i87;
            if (sha1_32(bArr) == -62563817) {
                break;
            }
        }
        for (int i88 = 63; i88 < 83; i88++) {
            bArr[84] = (byte) i88;
            if (sha1_32(bArr) == -990857812) {
                break;
            }
        }
        for (int i89 = 30; i89 < 48; i89++) {
            bArr[85] = (byte) i89;
            if (sha1_32(bArr) == -468010743) {
                break;
            }
        }
        for (int i90 = 27; i90 < 37; i90++) {
            bArr[86] = (byte) i90;
            if (sha1_32(bArr) == -1079691663) {
                break;
            }
        }
        for (int i91 = 21; i91 < 41; i91++) {
            bArr[87] = (byte) i91;
            if (sha1_32(bArr) == 1075770408) {
                break;
            }
        }
        for (int i92 = 9; i92 < 27; i92++) {
            bArr[88] = (byte) i92;
            if (sha1_32(bArr) == 165111714) {
                break;
            }
        }
        for (int i93 = -66; i93 < -58; i93++) {
            bArr[89] = (byte) i93;
            if (sha1_32(bArr) == -996370048) {
                break;
            }
        }
        for (int i94 = -66; i94 < -47; i94++) {
            bArr[90] = (byte) i94;
            if (sha1_32(bArr) == 1722619419) {
                break;
            }
        }
        for (int i95 = -67; i95 < -43; i95++) {
            bArr[91] = (byte) i95;
            if (sha1_32(bArr) == -344586126) {
                break;
            }
        }
        for (int i96 = 52; i96 < 66; i96++) {
            bArr[92] = (byte) i96;
            if (sha1_32(bArr) == 918634139) {
                break;
            }
        }
        for (int i97 = 83; i97 < 100; i97++) {
            bArr[93] = (byte) i97;
            if (sha1_32(bArr) == -713333830) {
                break;
            }
        }
        for (int i98 = -24; i98 < -8; i98++) {
            bArr[94] = (byte) i98;
            if (sha1_32(bArr) == 1194220475) {
                break;
            }
        }
        for (int i99 = 98; i99 < 122; i99++) {
            bArr[95] = (byte) i99;
            if (sha1_32(bArr) == -715805473) {
                break;
            }
        }
        for (int i100 = 112; i100 < 128; i100++) {
            bArr[96] = (byte) i100;
            if (sha1_32(bArr) == -77317629) {
                break;
            }
        }
        for (int i101 = 32; i101 < 53; i101++) {
            bArr[97] = (byte) i101;
            if (sha1_32(bArr) == 319550964) {
                break;
            }
        }
        for (int i102 = DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12; i102 < 128; i102++) {
            bArr[98] = (byte) i102;
            if (sha1_32(bArr) == -1650857711) {
                break;
            }
        }
        for (int i103 = -89; i103 < -75; i103++) {
            bArr[99] = (byte) i103;
            if (sha1_32(bArr) == -2079375028) {
                break;
            }
        }
        for (int i104 = -128; i104 < -117; i104++) {
            bArr[100] = (byte) i104;
            if (sha1_32(bArr) == 682150688) {
                break;
            }
        }
        for (int i105 = 103; i105 < 110; i105++) {
            bArr[101] = (byte) i105;
            if (sha1_32(bArr) == -1020758868) {
                break;
            }
        }
        for (int i106 = 30; i106 < 38; i106++) {
            bArr[102] = (byte) i106;
            if (sha1_32(bArr) == -173509717) {
                break;
            }
        }
        for (int i107 = -84; i107 < -71; i107++) {
            bArr[103] = (byte) i107;
            if (sha1_32(bArr) == 1485926635) {
                break;
            }
        }
        for (int i108 = -15; i108 < -2; i108++) {
            bArr[104] = (byte) i108;
            if (sha1_32(bArr) == -297493533) {
                break;
            }
        }
        for (int i109 = -18; i109 < 5; i109++) {
            bArr[105] = (byte) i109;
            if (sha1_32(bArr) == 1208048470) {
                break;
            }
        }
        for (int i110 = -112; i110 < -102; i110++) {
            bArr[106] = (byte) i110;
            if (sha1_32(bArr) == 626727770) {
                break;
            }
        }
        for (int i111 = -116; i111 < -89; i111++) {
            bArr[107] = (byte) i111;
            if (sha1_32(bArr) == 123824797) {
                break;
            }
        }
        for (int i112 = ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT; i112 < 128; i112++) {
            bArr[108] = (byte) i112;
            if (sha1_32(bArr) == -122561325) {
                break;
            }
        }
        for (int i113 = -94; i113 < -81; i113++) {
            bArr[109] = (byte) i113;
            if (sha1_32(bArr) == 952295875) {
                break;
            }
        }
        for (int i114 = 46; i114 < 70; i114++) {
            bArr[110] = (byte) i114;
            if (sha1_32(bArr) == 2138259851) {
                break;
            }
        }
        for (int i115 = 93; i115 < 105; i115++) {
            bArr[111] = (byte) i115;
            if (sha1_32(bArr) == -965829910) {
                break;
            }
        }
        for (int i116 = 70; i116 < 72; i116++) {
            bArr[112] = (byte) i116;
            if (sha1_32(bArr) == -1621557144) {
                break;
            }
        }
        for (int i117 = 83; i117 < 102; i117++) {
            bArr[113] = (byte) i117;
            if (sha1_32(bArr) == -466509669) {
                break;
            }
        }
        for (int i118 = 25; i118 < 41; i118++) {
            bArr[114] = (byte) i118;
            if (sha1_32(bArr) == 868674277) {
                break;
            }
        }
        for (int i119 = 69; i119 < 78; i119++) {
            bArr[115] = (byte) i119;
            if (sha1_32(bArr) == 877752600) {
                break;
            }
        }
        for (int i120 = 62; i120 < 79; i120++) {
            bArr[116] = (byte) i120;
            if (sha1_32(bArr) == -643435419) {
                break;
            }
        }
        for (int i121 = -67; i121 < -48; i121++) {
            bArr[117] = (byte) i121;
            if (sha1_32(bArr) == -1919631035) {
                break;
            }
        }
        for (int i122 = -86; i122 < -68; i122++) {
            bArr[118] = (byte) i122;
            if (sha1_32(bArr) == -111074345) {
                break;
            }
        }
        for (int i123 = -62; i123 < -60; i123++) {
            bArr[119] = (byte) i123;
            if (sha1_32(bArr) == 1597958839) {
                break;
            }
        }
        for (int i124 = -60; i124 < -30; i124++) {
            bArr[120] = (byte) i124;
            if (sha1_32(bArr) == -156647583) {
                break;
            }
        }
        for (int i125 = 51; i125 < 79; i125++) {
            bArr[121] = (byte) i125;
            if (sha1_32(bArr) == 555564435) {
                break;
            }
        }
        for (int i126 = -109; i126 < -99; i126++) {
            bArr[122] = (byte) i126;
            if (sha1_32(bArr) == 1002498815) {
                break;
            }
        }
        for (int i127 = -29; i127 < -21; i127++) {
            bArr[123] = (byte) i127;
            if (sha1_32(bArr) == -838958490) {
                break;
            }
        }
        for (int i128 = 118; i128 < 128; i128++) {
            bArr[124] = (byte) i128;
            if (sha1_32(bArr) == -355997854) {
                break;
            }
        }
        for (int i129 = -118; i129 < -104; i129++) {
            bArr[125] = (byte) i129;
            if (sha1_32(bArr) == 1748653116) {
                break;
            }
        }
        for (int i130 = 48; i130 < 62; i130++) {
            bArr[126] = (byte) i130;
            if (sha1_32(bArr) == 830112899) {
                break;
            }
        }
        for (int i131 = 56; i131 < 73; i131++) {
            bArr[127] = (byte) i131;
            if (sha1_32(bArr) == -1232124657) {
                break;
            }
        }
        for (int i132 = 106; i132 < 116; i132++) {
            bArr[128] = (byte) i132;
            if (sha1_32(bArr) == 1420870739) {
                break;
            }
        }
        for (int i133 = 22; i133 < 27; i133++) {
            bArr[129] = (byte) i133;
            if (sha1_32(bArr) == 1641873712) {
                break;
            }
        }
        for (int i134 = -69; i134 < -58; i134++) {
            bArr[130] = (byte) i134;
            if (sha1_32(bArr) == -193008657) {
                break;
            }
        }
        for (int i135 = -12; i135 < 5; i135++) {
            bArr[131] = (byte) i135;
            if (sha1_32(bArr) == -362511199) {
                break;
            }
        }
        for (int i136 = 3; i136 < 30; i136++) {
            bArr[132] = (byte) i136;
            if (sha1_32(bArr) == 59896139) {
                break;
            }
        }
        for (int i137 = -80; i137 < -64; i137++) {
            bArr[133] = (byte) i137;
            if (sha1_32(bArr) == 675714169) {
                break;
            }
        }
        for (int i138 = -50; i138 < -28; i138++) {
            bArr[134] = (byte) i138;
            if (sha1_32(bArr) == 138527488) {
                break;
            }
        }
        for (int i139 = 91; i139 < 117; i139++) {
            bArr[135] = (byte) i139;
            if (sha1_32(bArr) == -1012942471) {
                break;
            }
        }
        for (int i140 = 54; i140 < 76; i140++) {
            bArr[136] = (byte) i140;
            if (sha1_32(bArr) == -1025403341) {
                break;
            }
        }
        for (int i141 = -123; i141 < -104; i141++) {
            bArr[137] = (byte) i141;
            if (sha1_32(bArr) == 1031963448) {
                break;
            }
        }
        for (int i142 = 73; i142 < 76; i142++) {
            bArr[138] = (byte) i142;
            if (sha1_32(bArr) == -235799786) {
                break;
            }
        }
        for (int i143 = 41; i143 < 56; i143++) {
            bArr[139] = (byte) i143;
            if (sha1_32(bArr) == 1149077865) {
                break;
            }
        }
        for (int i144 = 87; i144 < 106; i144++) {
            bArr[140] = (byte) i144;
            if (sha1_32(bArr) == -2102123747) {
                break;
            }
        }
        for (int i145 = -3; i145 < 16; i145++) {
            bArr[141] = (byte) i145;
            if (sha1_32(bArr) == -253034998) {
                break;
            }
        }
        for (int i146 = 92; i146 < 97; i146++) {
            bArr[142] = (byte) i146;
            if (sha1_32(bArr) == 1213895996) {
                break;
            }
        }
        for (int i147 = -124; i147 < -112; i147++) {
            bArr[143] = (byte) i147;
            if (sha1_32(bArr) == -285049260) {
                break;
            }
        }
        for (int i148 = 47; i148 < 68; i148++) {
            bArr[144] = (byte) i148;
            if (sha1_32(bArr) == -1514793750) {
                break;
            }
        }
        for (int i149 = 15; i149 < 36; i149++) {
            bArr[145] = (byte) i149;
            if (sha1_32(bArr) == 780088313) {
                break;
            }
        }
        for (int i150 = -65; i150 < -46; i150++) {
            bArr[146] = (byte) i150;
            if (sha1_32(bArr) == -1499616677) {
                break;
            }
        }
        for (int i151 = -112; i151 < -87; i151++) {
            bArr[147] = (byte) i151;
            if (sha1_32(bArr) == -20789674) {
                break;
            }
        }
        for (int i152 = -78; i152 < -73; i152++) {
            bArr[148] = (byte) i152;
            if (sha1_32(bArr) == 1045594582) {
                break;
            }
        }
        for (int i153 = 49; i153 < 61; i153++) {
            bArr[149] = (byte) i153;
            if (sha1_32(bArr) == -234897156) {
                break;
            }
        }
        for (int i154 = -25; i154 < -9; i154++) {
            bArr[150] = (byte) i154;
            if (sha1_32(bArr) == -555818468) {
                break;
            }
        }
        for (int i155 = -99; i155 < -71; i155++) {
            bArr[151] = (byte) i155;
            if (sha1_32(bArr) == -2134865754) {
                break;
            }
        }
        for (int i156 = -6; i156 < 21; i156++) {
            bArr[152] = (byte) i156;
            if (sha1_32(bArr) == 609552888) {
                break;
            }
        }
        for (int i157 = -117; i157 < -103; i157++) {
            bArr[153] = (byte) i157;
            if (sha1_32(bArr) == -271187608) {
                break;
            }
        }
        for (int i158 = -118; i158 < -107; i158++) {
            bArr[154] = (byte) i158;
            if (sha1_32(bArr) == -920970460) {
                break;
            }
        }
        for (int i159 = 45; i159 < 74; i159++) {
            bArr[155] = (byte) i159;
            if (sha1_32(bArr) == -1508515618) {
                break;
            }
        }
        for (int i160 = -43; i160 < -18; i160++) {
            bArr[156] = (byte) i160;
            if (sha1_32(bArr) == -1123012305) {
                break;
            }
        }
        for (int i161 = -51; i161 < -28; i161++) {
            bArr[157] = (byte) i161;
            if (sha1_32(bArr) == 1792137198) {
                break;
            }
        }
        for (int i162 = -44; i162 < -15; i162++) {
            bArr[158] = (byte) i162;
            if (sha1_32(bArr) == 1655716526) {
                break;
            }
        }
        for (int i163 = 13; i163 < 29; i163++) {
            bArr[159] = (byte) i163;
            if (sha1_32(bArr) == 1916482481) {
                break;
            }
        }
        for (int i164 = -114; i164 < -99; i164++) {
            bArr[160] = (byte) i164;
            if (sha1_32(bArr) == 1216452824) {
                break;
            }
        }
        for (int i165 = 101; i165 < 128; i165++) {
            bArr[161] = (byte) i165;
            if (sha1_32(bArr) == -153817351) {
                break;
            }
        }
        for (int i166 = -63; i166 < -48; i166++) {
            bArr[162] = (byte) i166;
            if (sha1_32(bArr) == -791291750) {
                break;
            }
        }
        for (int i167 = 85; i167 < 100; i167++) {
            bArr[163] = (byte) i167;
            if (sha1_32(bArr) == 329303098) {
                break;
            }
        }
        for (int i168 = -124; i168 < -113; i168++) {
            bArr[164] = (byte) i168;
            if (sha1_32(bArr) == -67320412) {
                break;
            }
        }
        for (int i169 = 97; i169 < 125; i169++) {
            bArr[165] = (byte) i169;
            if (sha1_32(bArr) == -2124345787) {
                break;
            }
        }
        for (int i170 = -49; i170 < -29; i170++) {
            bArr[166] = (byte) i170;
            if (sha1_32(bArr) == 1136874206) {
                break;
            }
        }
        for (int i171 = 57; i171 < 82; i171++) {
            bArr[167] = (byte) i171;
            if (sha1_32(bArr) == 1700654877) {
                break;
            }
        }
        for (int i172 = 91; i172 < 104; i172++) {
            bArr[168] = (byte) i172;
            if (sha1_32(bArr) == 539275847) {
                break;
            }
        }
        for (int i173 = 42; i173 < 56; i173++) {
            bArr[169] = (byte) i173;
            if (sha1_32(bArr) == 2018847500) {
                break;
            }
        }
        for (int i174 = 83; i174 < 100; i174++) {
            bArr[170] = (byte) i174;
            if (sha1_32(bArr) == 411395268) {
                break;
            }
        }
        for (int i175 = -8; i175 < 6; i175++) {
            bArr[171] = (byte) i175;
            if (sha1_32(bArr) == -1660541030) {
                break;
            }
        }
        for (int i176 = 19; i176 < 37; i176++) {
            bArr[172] = (byte) i176;
            if (sha1_32(bArr) == 1429087389) {
                break;
            }
        }
        for (int i177 = 60; i177 < 74; i177++) {
            bArr[173] = (byte) i177;
            if (sha1_32(bArr) == -234438281) {
                break;
            }
        }
        for (int i178 = -42; i178 < -32; i178++) {
            bArr[174] = (byte) i178;
            if (sha1_32(bArr) == 615919737) {
                break;
            }
        }
        for (int i179 = -103; i179 < -91; i179++) {
            bArr[175] = (byte) i179;
            if (sha1_32(bArr) == 193023555) {
                break;
            }
        }
        for (int i180 = -18; i180 < -5; i180++) {
            bArr[176] = (byte) i180;
            if (sha1_32(bArr) == 2146082938) {
                break;
            }
        }
        for (int i181 = 108; i181 < 126; i181++) {
            bArr[177] = (byte) i181;
            if (sha1_32(bArr) == 1934424898) {
                break;
            }
        }
        for (int i182 = 32; i182 < 57; i182++) {
            bArr[178] = (byte) i182;
            if (sha1_32(bArr) == -1990478521) {
                break;
            }
        }
        for (int i183 = -52; i183 < -44; i183++) {
            bArr[179] = (byte) i183;
            if (sha1_32(bArr) == 1902545697) {
                break;
            }
        }
        for (int i184 = -94; i184 < -65; i184++) {
            bArr[180] = (byte) i184;
            if (sha1_32(bArr) == 197648899) {
                break;
            }
        }
        for (int i185 = -60; i185 < -42; i185++) {
            bArr[181] = (byte) i185;
            if (sha1_32(bArr) == -1612144330) {
                break;
            }
        }
        for (int i186 = -97; i186 < -86; i186++) {
            bArr[182] = (byte) i186;
            if (sha1_32(bArr) == 571390237) {
                break;
            }
        }
        for (int i187 = -36; i187 < -18; i187++) {
            bArr[183] = (byte) i187;
            if (sha1_32(bArr) == 695299318) {
                break;
            }
        }
        for (int i188 = -9; i188 < 15; i188++) {
            bArr[184] = (byte) i188;
            if (sha1_32(bArr) == -1074909704) {
                break;
            }
        }
        for (int i189 = -15; i189 < 11; i189++) {
            bArr[185] = (byte) i189;
            if (sha1_32(bArr) == -449365562) {
                break;
            }
        }
        for (int i190 = 82; i190 < 100; i190++) {
            bArr[186] = (byte) i190;
            if (sha1_32(bArr) == 2054461933) {
                break;
            }
        }
        for (int i191 = -62; i191 < -55; i191++) {
            bArr[187] = (byte) i191;
            if (sha1_32(bArr) == 1407727709) {
                break;
            }
        }
        for (int i192 = 119; i192 < 128; i192++) {
            bArr[188] = (byte) i192;
            if (sha1_32(bArr) == 1741309675) {
                break;
            }
        }
        for (int i193 = -54; i193 < -31; i193++) {
            bArr[189] = (byte) i193;
            if (sha1_32(bArr) == 2096236609) {
                break;
            }
        }
        for (int i194 = -16; i194 < -5; i194++) {
            bArr[190] = (byte) i194;
            if (sha1_32(bArr) == 2062860725) {
                break;
            }
        }
        for (int i195 = -96; i195 < -81; i195++) {
            bArr[191] = (byte) i195;
            if (sha1_32(bArr) == -1833650597) {
                break;
            }
        }
        for (int i196 = 28; i196 < 39; i196++) {
            bArr[192] = (byte) i196;
            if (sha1_32(bArr) == -1377436669) {
                break;
            }
        }
        for (int i197 = 63; i197 < 71; i197++) {
            bArr[193] = (byte) i197;
            if (sha1_32(bArr) == 1289353187) {
                break;
            }
        }
        for (int i198 = 80; i198 < 99; i198++) {
            bArr[194] = (byte) i198;
            if (sha1_32(bArr) == -2043653959) {
                break;
            }
        }
        for (int i199 = -4; i199 < 19; i199++) {
            bArr[195] = (byte) i199;
            if (sha1_32(bArr) == -1156195349) {
                break;
            }
        }
        for (int i200 = -21; i200 < -13; i200++) {
            bArr[196] = (byte) i200;
            if (sha1_32(bArr) == 362905650) {
                break;
            }
        }
        for (int i201 = 90; i201 < 111; i201++) {
            bArr[197] = (byte) i201;
            if (sha1_32(bArr) == -1751030256) {
                break;
            }
        }
        for (int i202 = -107; i202 < -85; i202++) {
            bArr[198] = (byte) i202;
            if (sha1_32(bArr) == 1523853403) {
                break;
            }
        }
        for (int i203 = -8; i203 < 4; i203++) {
            bArr[199] = (byte) i203;
            if (sha1_32(bArr) == -1055694771) {
                break;
            }
        }
        for (int i204 = 46; i204 < 61; i204++) {
            bArr[200] = (byte) i204;
            if (sha1_32(bArr) == 72698401) {
                break;
            }
        }
        for (int i205 = 41; i205 < 63; i205++) {
            bArr[201] = (byte) i205;
            if (sha1_32(bArr) == 1109574833) {
                break;
            }
        }
        for (int i206 = 75; i206 < 102; i206++) {
            bArr[202] = (byte) i206;
            if (sha1_32(bArr) == 1851678897) {
                break;
            }
        }
        for (int i207 = 49; i207 < 69; i207++) {
            bArr[203] = (byte) i207;
            if (sha1_32(bArr) == -1815825395) {
                break;
            }
        }
        for (int i208 = -71; i208 < -55; i208++) {
            bArr[204] = (byte) i208;
            if (sha1_32(bArr) == -1131461980) {
                break;
            }
        }
        for (int i209 = 77; i209 < 102; i209++) {
            bArr[205] = (byte) i209;
            if (sha1_32(bArr) == 1496487223) {
                break;
            }
        }
        for (int i210 = 85; i210 < 100; i210++) {
            bArr[206] = (byte) i210;
            if (sha1_32(bArr) == -1014508350) {
                break;
            }
        }
        bArr[207] = (byte) 96;
        sha1_32(bArr);
        for (int i211 = 51; i211 < 63; i211++) {
            bArr[208] = (byte) i211;
            if (sha1_32(bArr) == -410879093) {
                break;
            }
        }
        for (int i212 = 33; i212 < 45; i212++) {
            bArr[209] = (byte) i212;
            if (sha1_32(bArr) == 44287866) {
                break;
            }
        }
        for (int i213 = 32; i213 < 40; i213++) {
            bArr[210] = (byte) i213;
            if (sha1_32(bArr) == -990851552) {
                break;
            }
        }
        for (int i214 = 31; i214 < 58; i214++) {
            bArr[211] = (byte) i214;
            if (sha1_32(bArr) == -154159676) {
                break;
            }
        }
        for (int i215 = -103; i215 < -81; i215++) {
            bArr[212] = (byte) i215;
            if (sha1_32(bArr) == 671104608) {
                break;
            }
        }
        for (int i216 = 0; i216 < 20; i216++) {
            bArr[213] = (byte) i216;
            if (sha1_32(bArr) == -1776603471) {
                break;
            }
        }
        for (int i217 = -100; i217 < -87; i217++) {
            bArr[214] = (byte) i217;
            if (sha1_32(bArr) == -681721141) {
                break;
            }
        }
        for (int i218 = 70; i218 < 87; i218++) {
            bArr[215] = (byte) i218;
            if (sha1_32(bArr) == -1382197385) {
                break;
            }
        }
        for (int i219 = 88; i219 < 114; i219++) {
            bArr[216] = (byte) i219;
            if (sha1_32(bArr) == 2010686617) {
                break;
            }
        }
        for (int i220 = -52; i220 < -25; i220++) {
            bArr[217] = (byte) i220;
            if (sha1_32(bArr) == -433599372) {
                break;
            }
        }
        for (int i221 = -20; i221 < -2; i221++) {
            bArr[218] = (byte) i221;
            if (sha1_32(bArr) == 1736772764) {
                break;
            }
        }
        for (int i222 = -94; i222 < -80; i222++) {
            bArr[219] = (byte) i222;
            if (sha1_32(bArr) == -469650379) {
                break;
            }
        }
        for (int i223 = 4; i223 < 17; i223++) {
            bArr[220] = (byte) i223;
            if (sha1_32(bArr) == -1047144355) {
                break;
            }
        }
        for (int i224 = 8; i224 < 17; i224++) {
            bArr[221] = (byte) i224;
            if (sha1_32(bArr) == 682534153) {
                break;
            }
        }
        for (int i225 = -30; i225 < -6; i225++) {
            bArr[222] = (byte) i225;
            if (sha1_32(bArr) == -785732359) {
                break;
            }
        }
        for (int i226 = 50; i226 < 54; i226++) {
            bArr[223] = (byte) i226;
            if (sha1_32(bArr) == -914368466) {
                break;
            }
        }
        for (int i227 = 74; i227 < 97; i227++) {
            bArr[224] = (byte) i227;
            if (sha1_32(bArr) == -314157582) {
                break;
            }
        }
        for (int i228 = -14; i228 < 2; i228++) {
            bArr[225] = (byte) i228;
            if (sha1_32(bArr) == -1961797413) {
                break;
            }
        }
        for (int i229 = -4; i229 < 16; i229++) {
            bArr[226] = (byte) i229;
            if (sha1_32(bArr) == -686204435) {
                break;
            }
        }
        for (int i230 = 39; i230 < 56; i230++) {
            bArr[227] = (byte) i230;
            if (sha1_32(bArr) == -505430470) {
                break;
            }
        }
        for (int i231 = -62; i231 < -42; i231++) {
            bArr[228] = (byte) i231;
            if (sha1_32(bArr) == 991458549) {
                break;
            }
        }
        for (int i232 = 83; i232 < 104; i232++) {
            bArr[229] = (byte) i232;
            if (sha1_32(bArr) == -264702079) {
                break;
            }
        }
        for (int i233 = -2; i233 < 10; i233++) {
            bArr[230] = (byte) i233;
            if (sha1_32(bArr) == -1375168850) {
                break;
            }
        }
        for (int i234 = 22; i234 < 30; i234++) {
            bArr[231] = (byte) i234;
            if (sha1_32(bArr) == 627779998) {
                break;
            }
        }
        for (int i235 = -102; i235 < -86; i235++) {
            bArr[232] = (byte) i235;
            if (sha1_32(bArr) == -1981682635) {
                break;
            }
        }
        for (int i236 = 72; i236 < 90; i236++) {
            bArr[233] = (byte) i236;
            if (sha1_32(bArr) == -1286131825) {
                break;
            }
        }
        for (int i237 = -116; i237 < -98; i237++) {
            bArr[234] = (byte) i237;
            if (sha1_32(bArr) == -351956961) {
                break;
            }
        }
        for (int i238 = 88; i238 < 107; i238++) {
            bArr[235] = (byte) i238;
            if (sha1_32(bArr) == -715468425) {
                break;
            }
        }
        for (int i239 = -47; i239 < -43; i239++) {
            bArr[236] = (byte) i239;
            if (sha1_32(bArr) == -2075215994) {
                break;
            }
        }
        for (int i240 = 56; i240 < 71; i240++) {
            bArr[237] = (byte) i240;
            if (sha1_32(bArr) == -1883065750) {
                break;
            }
        }
        for (int i241 = -127; i241 < -109; i241++) {
            bArr[238] = (byte) i241;
            if (sha1_32(bArr) == 1107509541) {
                break;
            }
        }
        for (int i242 = 76; i242 < 94; i242++) {
            bArr[239] = (byte) i242;
            if (sha1_32(bArr) == 752883232) {
                break;
            }
        }
        for (int i243 = 60; i243 < 75; i243++) {
            bArr[240] = (byte) i243;
            if (sha1_32(bArr) == -1895179257) {
                break;
            }
        }
        for (int i244 = -128; i244 < -108; i244++) {
            bArr[241] = (byte) i244;
            if (sha1_32(bArr) == -1783417797) {
                break;
            }
        }
        for (int i245 = -82; i245 < -70; i245++) {
            bArr[242] = (byte) i245;
            if (sha1_32(bArr) == 1665312276) {
                break;
            }
        }
        for (int i246 = 27; i246 < 49; i246++) {
            bArr[243] = (byte) i246;
            if (sha1_32(bArr) == 2142973821) {
                break;
            }
        }
        for (int i247 = -61; i247 < -36; i247++) {
            bArr[244] = (byte) i247;
            if (sha1_32(bArr) == 1724149725) {
                break;
            }
        }
        for (int i248 = 14; i248 < 27; i248++) {
            bArr[245] = (byte) i248;
            if (sha1_32(bArr) == 399634793) {
                break;
            }
        }
        for (int i249 = 0; i249 < 23; i249++) {
            bArr[246] = (byte) i249;
            if (sha1_32(bArr) == 2001483225) {
                break;
            }
        }
        for (int i250 = -1; i250 < 19; i250++) {
            bArr[247] = (byte) i250;
            if (sha1_32(bArr) == 836679972) {
                break;
            }
        }
        for (int i251 = -27; i251 < -9; i251++) {
            bArr[248] = (byte) i251;
            if (sha1_32(bArr) == 206182108) {
                break;
            }
        }
        for (int i252 = -83; i252 < -62; i252++) {
            bArr[249] = (byte) i252;
            if (sha1_32(bArr) == 325388776) {
                break;
            }
        }
        for (int i253 = 49; i253 < 68; i253++) {
            bArr[250] = (byte) i253;
            if (sha1_32(bArr) == -287579539) {
                break;
            }
        }
        for (int i254 = 29; i254 < 46; i254++) {
            bArr[251] = (byte) i254;
            if (sha1_32(bArr) == -1982667057) {
                break;
            }
        }
        for (int i255 = 15; i255 < 39; i255++) {
            bArr[252] = (byte) i255;
            if (sha1_32(bArr) == -417773801) {
                break;
            }
        }
        for (int i256 = -54; i256 < -30; i256++) {
            bArr[253] = (byte) i256;
            if (sha1_32(bArr) == 991503427) {
                break;
            }
        }
        for (int i257 = -107; i257 < -97; i257++) {
            bArr[254] = (byte) i257;
            if (sha1_32(bArr) == -294844533) {
                break;
            }
        }
        for (int i258 = -4; i258 < 12; i258++) {
            bArr[255] = (byte) i258;
            if (sha1_32(bArr) == 700552299) {
                break;
            }
        }
        for (int i259 = UiPosIndexEnum.HOME_COUNTDOWN; i259 < 127; i259++) {
            bArr[256] = (byte) i259;
            if (sha1_32(bArr) == -477004030) {
                break;
            }
        }
        for (int i260 = 99; i260 < 107; i260++) {
            bArr[257] = (byte) i260;
            if (sha1_32(bArr) == -1536530669) {
                break;
            }
        }
        for (int i261 = 123; i261 < 128; i261++) {
            bArr[258] = (byte) i261;
            if (sha1_32(bArr) == -1120554181) {
                break;
            }
        }
        for (int i262 = -126; i262 < -110; i262++) {
            bArr[259] = (byte) i262;
            if (sha1_32(bArr) == 519622818) {
                break;
            }
        }
        for (int i263 = -90; i263 < -69; i263++) {
            bArr[260] = (byte) i263;
            if (sha1_32(bArr) == 1149057315) {
                break;
            }
        }
        for (int i264 = -103; i264 < -94; i264++) {
            bArr[261] = (byte) i264;
            if (sha1_32(bArr) == -1023648631) {
                break;
            }
        }
        for (int i265 = 62; i265 < 77; i265++) {
            bArr[262] = (byte) i265;
            if (sha1_32(bArr) == -1210861891) {
                break;
            }
        }
        for (int i266 = 74; i266 < 91; i266++) {
            bArr[263] = (byte) i266;
            if (sha1_32(bArr) == 655214406) {
                break;
            }
        }
        for (int i267 = -66; i267 < -60; i267++) {
            bArr[264] = (byte) i267;
            if (sha1_32(bArr) == 1912587907) {
                break;
            }
        }
        for (int i268 = 96; i268 < 109; i268++) {
            bArr[265] = (byte) i268;
            if (sha1_32(bArr) == 1214005372) {
                break;
            }
        }
        for (int i269 = -86; i269 < -74; i269++) {
            bArr[266] = (byte) i269;
            if (sha1_32(bArr) == 1011811122) {
                break;
            }
        }
        for (int i270 = -101; i270 < -88; i270++) {
            bArr[267] = (byte) i270;
            if (sha1_32(bArr) == 1269268263) {
                break;
            }
        }
        for (int i271 = -7; i271 < 4; i271++) {
            bArr[268] = (byte) i271;
            if (sha1_32(bArr) == -1567524658) {
                break;
            }
        }
        for (int i272 = 92; i272 < 112; i272++) {
            bArr[269] = (byte) i272;
            if (sha1_32(bArr) == 2045202504) {
                break;
            }
        }
        for (int i273 = 50; i273 < 61; i273++) {
            bArr[270] = (byte) i273;
            if (sha1_32(bArr) == 1699554441) {
                break;
            }
        }
        for (int i274 = 76; i274 < 87; i274++) {
            bArr[271] = (byte) i274;
            if (sha1_32(bArr) == -1985968782) {
                break;
            }
        }
        for (int i275 = 10; i275 < 27; i275++) {
            bArr[272] = (byte) i275;
            if (sha1_32(bArr) == 932857178) {
                break;
            }
        }
        for (int i276 = -100; i276 < -89; i276++) {
            bArr[273] = (byte) i276;
            if (sha1_32(bArr) == -2107106323) {
                break;
            }
        }
        for (int i277 = 93; i277 < 117; i277++) {
            bArr[274] = (byte) i277;
            if (sha1_32(bArr) == -57686262) {
                break;
            }
        }
        for (int i278 = -14; i278 < 10; i278++) {
            bArr[275] = (byte) i278;
            if (sha1_32(bArr) == 1027512615) {
                break;
            }
        }
        for (int i279 = -6; i279 < 2; i279++) {
            bArr[276] = (byte) i279;
            if (sha1_32(bArr) == 914779930) {
                break;
            }
        }
        for (int i280 = -50; i280 < -33; i280++) {
            bArr[277] = (byte) i280;
            if (sha1_32(bArr) == -1155750880) {
                break;
            }
        }
        for (int i281 = 85; i281 < 105; i281++) {
            bArr[278] = (byte) i281;
            if (sha1_32(bArr) == -1907044524) {
                break;
            }
        }
        for (int i282 = 103; i282 < 128; i282++) {
            bArr[279] = (byte) i282;
            if (sha1_32(bArr) == 60405384) {
                break;
            }
        }
        for (int i283 = -91; i283 < -71; i283++) {
            bArr[280] = (byte) i283;
            if (sha1_32(bArr) == 238476270) {
                break;
            }
        }
        for (int i284 = -31; i284 < -12; i284++) {
            bArr[281] = (byte) i284;
            if (sha1_32(bArr) == 1137587149) {
                break;
            }
        }
        for (int i285 = -22; i285 < 2; i285++) {
            bArr[282] = (byte) i285;
            if (sha1_32(bArr) == 1045225256) {
                break;
            }
        }
        for (int i286 = -32; i286 < -21; i286++) {
            bArr[283] = (byte) i286;
            if (sha1_32(bArr) == -1692615633) {
                break;
            }
        }
        for (int i287 = 15; i287 < 30; i287++) {
            bArr[284] = (byte) i287;
            if (sha1_32(bArr) == 535311184) {
                break;
            }
        }
        for (int i288 = -107; i288 < -81; i288++) {
            bArr[285] = (byte) i288;
            if (sha1_32(bArr) == -1618352923) {
                break;
            }
        }
        for (int i289 = 111; i289 < 128; i289++) {
            bArr[286] = (byte) i289;
            if (sha1_32(bArr) == -1812955103) {
                break;
            }
        }
        for (int i290 = -98; i290 < -70; i290++) {
            bArr[287] = (byte) i290;
            if (sha1_32(bArr) == -1217464319) {
                break;
            }
        }
        for (int i291 = -81; i291 < -61; i291++) {
            bArr[288] = (byte) i291;
            if (sha1_32(bArr) == -490199504) {
                break;
            }
        }
        for (int i292 = -51; i292 < -34; i292++) {
            bArr[289] = (byte) i292;
            if (sha1_32(bArr) == -2032080905) {
                break;
            }
        }
        for (int i293 = -31; i293 < -14; i293++) {
            bArr[290] = (byte) i293;
            if (sha1_32(bArr) == -1584394471) {
                break;
            }
        }
        for (int i294 = -31; i294 < -16; i294++) {
            bArr[291] = (byte) i294;
            if (sha1_32(bArr) == -775083667) {
                break;
            }
        }
        for (int i295 = 24; i295 < 37; i295++) {
            bArr[292] = (byte) i295;
            if (sha1_32(bArr) == 1276983021) {
                break;
            }
        }
        for (int i296 = -90; i296 < -80; i296++) {
            bArr[293] = (byte) i296;
            if (sha1_32(bArr) == 2144770960) {
                break;
            }
        }
        for (int i297 = 83; i297 < 101; i297++) {
            bArr[294] = (byte) i297;
            if (sha1_32(bArr) == 302746139) {
                break;
            }
        }
        for (int i298 = 5; i298 < 16; i298++) {
            bArr[295] = (byte) i298;
            if (sha1_32(bArr) == 801293364) {
                break;
            }
        }
        for (int i299 = 109; i299 < 128; i299++) {
            bArr[296] = (byte) i299;
            if (sha1_32(bArr) == -640307470) {
                break;
            }
        }
        for (int i300 = -31; i300 < -9; i300++) {
            bArr[297] = (byte) i300;
            if (sha1_32(bArr) == -1219763919) {
                break;
            }
        }
        for (int i301 = 82; i301 < 93; i301++) {
            bArr[298] = (byte) i301;
            if (sha1_32(bArr) == 1472403091) {
                break;
            }
        }
        for (int i302 = -94; i302 < -78; i302++) {
            bArr[299] = (byte) i302;
            if (sha1_32(bArr) == -1394508771) {
                break;
            }
        }
        for (int i303 = 86; i303 < 103; i303++) {
            bArr[300] = (byte) i303;
            if (sha1_32(bArr) == 311884002) {
                break;
            }
        }
        for (int i304 = -91; i304 < -74; i304++) {
            bArr[301] = (byte) i304;
            if (sha1_32(bArr) == -1132823573) {
                break;
            }
        }
        for (int i305 = -88; i305 < -80; i305++) {
            bArr[302] = (byte) i305;
            if (sha1_32(bArr) == 268408854) {
                break;
            }
        }
        for (int i306 = -46; i306 < -34; i306++) {
            bArr[303] = (byte) i306;
            if (sha1_32(bArr) == -735616268) {
                break;
            }
        }
        for (int i307 = -14; i307 < 9; i307++) {
            bArr[304] = (byte) i307;
            if (sha1_32(bArr) == -280732666) {
                break;
            }
        }
        for (int i308 = 93; i308 < 110; i308++) {
            bArr[305] = (byte) i308;
            if (sha1_32(bArr) == 436797463) {
                break;
            }
        }
        for (int i309 = 42; i309 < 62; i309++) {
            bArr[306] = (byte) i309;
            if (sha1_32(bArr) == -367018834) {
                break;
            }
        }
        for (int i310 = -21; i310 < 10; i310++) {
            bArr[307] = (byte) i310;
            if (sha1_32(bArr) == 1510617646) {
                break;
            }
        }
        for (int i311 = 99; i311 < 124; i311++) {
            bArr[308] = (byte) i311;
            if (sha1_32(bArr) == 1323212423) {
                break;
            }
        }
        for (int i312 = -85; i312 < -70; i312++) {
            bArr[309] = (byte) i312;
            if (sha1_32(bArr) == 1127321672) {
                break;
            }
        }
        for (int i313 = -115; i313 < -100; i313++) {
            bArr[310] = (byte) i313;
            if (sha1_32(bArr) == -1488370707) {
                break;
            }
        }
        for (int i314 = -35; i314 < -15; i314++) {
            bArr[311] = (byte) i314;
            if (sha1_32(bArr) == 612977506) {
                break;
            }
        }
        for (int i315 = -63; i315 < -49; i315++) {
            bArr[312] = (byte) i315;
            if (sha1_32(bArr) == -361418534) {
                break;
            }
        }
        byte b = (byte) (-128);
        bArr[313] = b;
        sha1_32(bArr);
        for (int i316 = -19; i316 < -15; i316++) {
            bArr[314] = (byte) i316;
            if (sha1_32(bArr) == -1170941258) {
                break;
            }
        }
        for (int i317 = -122; i317 < -118; i317++) {
            bArr[315] = (byte) i317;
            if (sha1_32(bArr) == 1650724386) {
                break;
            }
        }
        for (int i318 = 10; i318 < 22; i318++) {
            bArr[316] = (byte) i318;
            if (sha1_32(bArr) == -458442487) {
                break;
            }
        }
        for (int i319 = -124; i319 < -109; i319++) {
            bArr[317] = (byte) i319;
            if (sha1_32(bArr) == 30910677) {
                break;
            }
        }
        for (int i320 = 59; i320 < 76; i320++) {
            bArr[318] = (byte) i320;
            if (sha1_32(bArr) == 2002730279) {
                break;
            }
        }
        for (int i321 = -37; i321 < -12; i321++) {
            bArr[319] = (byte) i321;
            if (sha1_32(bArr) == 1439044148) {
                break;
            }
        }
        for (int i322 = 10; i322 < 19; i322++) {
            bArr[320] = (byte) i322;
            if (sha1_32(bArr) == -1924148371) {
                break;
            }
        }
        for (int i323 = -51; i323 < -27; i323++) {
            bArr[321] = (byte) i323;
            if (sha1_32(bArr) == 2135502965) {
                break;
            }
        }
        for (int i324 = -72; i324 < -46; i324++) {
            bArr[322] = (byte) i324;
            if (sha1_32(bArr) == 1924515962) {
                break;
            }
        }
        for (int i325 = -4; i325 < 13; i325++) {
            bArr[323] = (byte) i325;
            if (sha1_32(bArr) == 311983785) {
                break;
            }
        }
        for (int i326 = 2; i326 < 14; i326++) {
            bArr[324] = (byte) i326;
            if (sha1_32(bArr) == 2139788560) {
                break;
            }
        }
        for (int i327 = -109; i327 < -90; i327++) {
            bArr[325] = (byte) i327;
            if (sha1_32(bArr) == -479590568) {
                break;
            }
        }
        for (int i328 = -112; i328 < -99; i328++) {
            bArr[326] = (byte) i328;
            if (sha1_32(bArr) == -17645488) {
                break;
            }
        }
        for (int i329 = -30; i329 < -26; i329++) {
            bArr[327] = (byte) i329;
            if (sha1_32(bArr) == 283106268) {
                break;
            }
        }
        for (int i330 = 95; i330 < 112; i330++) {
            bArr[328] = (byte) i330;
            if (sha1_32(bArr) == 681186202) {
                break;
            }
        }
        for (int i331 = 104; i331 < 111; i331++) {
            bArr[329] = (byte) i331;
            if (sha1_32(bArr) == 334143954) {
                break;
            }
        }
        for (int i332 = 34; i332 < 51; i332++) {
            bArr[330] = (byte) i332;
            if (sha1_32(bArr) == -1406880708) {
                break;
            }
        }
        for (int i333 = -94; i333 < -80; i333++) {
            bArr[331] = (byte) i333;
            if (sha1_32(bArr) == -176195651) {
                break;
            }
        }
        for (int i334 = 55; i334 < 63; i334++) {
            bArr[332] = (byte) i334;
            if (sha1_32(bArr) == -562683183) {
                break;
            }
        }
        for (int i335 = -83; i335 < -68; i335++) {
            bArr[333] = (byte) i335;
            if (sha1_32(bArr) == -1686630299) {
                break;
            }
        }
        for (int i336 = 82; i336 < 95; i336++) {
            bArr[334] = (byte) i336;
            if (sha1_32(bArr) == -1885599122) {
                break;
            }
        }
        for (int i337 = -48; i337 < -31; i337++) {
            bArr[335] = (byte) i337;
            if (sha1_32(bArr) == 1392526617) {
                break;
            }
        }
        for (int i338 = -16; i338 < -3; i338++) {
            bArr[336] = (byte) i338;
            if (sha1_32(bArr) == -1856803474) {
                break;
            }
        }
        for (int i339 = -60; i339 < -49; i339++) {
            bArr[337] = (byte) i339;
            if (sha1_32(bArr) == 825159984) {
                break;
            }
        }
        for (int i340 = -123; i340 < -109; i340++) {
            bArr[338] = (byte) i340;
            if (sha1_32(bArr) == -724089678) {
                break;
            }
        }
        for (int i341 = -27; i341 < -2; i341++) {
            bArr[339] = (byte) i341;
            if (sha1_32(bArr) == 2102516084) {
                break;
            }
        }
        for (int i342 = 83; i342 < 94; i342++) {
            bArr[340] = (byte) i342;
            if (sha1_32(bArr) == -1536927171) {
                break;
            }
        }
        for (int i343 = 14; i343 < 25; i343++) {
            bArr[341] = (byte) i343;
            if (sha1_32(bArr) == 69820006) {
                break;
            }
        }
        for (int i344 = -59; i344 < -40; i344++) {
            bArr[342] = (byte) i344;
            if (sha1_32(bArr) == -1485069681) {
                break;
            }
        }
        for (int i345 = -78; i345 < -58; i345++) {
            bArr[343] = (byte) i345;
            if (sha1_32(bArr) == -1506998598) {
                break;
            }
        }
        for (int i346 = 39; i346 < 55; i346++) {
            bArr[344] = (byte) i346;
            if (sha1_32(bArr) == -1292314077) {
                break;
            }
        }
        for (int i347 = -30; i347 < -19; i347++) {
            bArr[345] = (byte) i347;
            if (sha1_32(bArr) == 321872910) {
                break;
            }
        }
        for (int i348 = -128; i348 < -114; i348++) {
            bArr[346] = (byte) i348;
            if (sha1_32(bArr) == -1288114485) {
                break;
            }
        }
        for (int i349 = 17; i349 < 39; i349++) {
            bArr[347] = (byte) i349;
            if (sha1_32(bArr) == 585914660) {
                break;
            }
        }
        for (int i350 = 35; i350 < 60; i350++) {
            bArr[348] = (byte) i350;
            if (sha1_32(bArr) == 1638472690) {
                break;
            }
        }
        for (int i351 = -112; i351 < -100; i351++) {
            bArr[349] = (byte) i351;
            if (sha1_32(bArr) == 1954759908) {
                break;
            }
        }
        for (int i352 = -58; i352 < -51; i352++) {
            bArr[350] = (byte) i352;
            if (sha1_32(bArr) == -456873899) {
                break;
            }
        }
        for (int i353 = -101; i353 < -84; i353++) {
            bArr[351] = (byte) i353;
            if (sha1_32(bArr) == -2118059700) {
                break;
            }
        }
        for (int i354 = -10; i354 < 11; i354++) {
            bArr[352] = (byte) i354;
            if (sha1_32(bArr) == -2118059700) {
                break;
            }
        }
        for (int i355 = -95; i355 < -74; i355++) {
            bArr[353] = (byte) i355;
            if (sha1_32(bArr) == 1785887430) {
                break;
            }
        }
        for (int i356 = -96; i356 < -76; i356++) {
            bArr[354] = (byte) i356;
            if (sha1_32(bArr) == 258669325) {
                break;
            }
        }
        for (int i357 = -123; i357 < -105; i357++) {
            bArr[355] = (byte) i357;
            if (sha1_32(bArr) == 2077075913) {
                break;
            }
        }
        for (int i358 = -39; i358 < -25; i358++) {
            bArr[356] = (byte) i358;
            if (sha1_32(bArr) == 1686620180) {
                break;
            }
        }
        for (int i359 = -99; i359 < -75; i359++) {
            bArr[357] = (byte) i359;
            if (sha1_32(bArr) == 1624201411) {
                break;
            }
        }
        for (int i360 = 72; i360 < 96; i360++) {
            bArr[358] = (byte) i360;
            if (sha1_32(bArr) == 239375660) {
                break;
            }
        }
        for (int i361 = -123; i361 < -114; i361++) {
            bArr[359] = (byte) i361;
            if (sha1_32(bArr) == 428081608) {
                break;
            }
        }
        for (int i362 = -111; i362 < -101; i362++) {
            bArr[360] = (byte) i362;
            if (sha1_32(bArr) == -257788426) {
                break;
            }
        }
        for (int i363 = -43; i363 < -30; i363++) {
            bArr[361] = (byte) i363;
            if (sha1_32(bArr) == 1046651261) {
                break;
            }
        }
        for (int i364 = 123; i364 < 127; i364++) {
            bArr[362] = (byte) i364;
            if (sha1_32(bArr) == -862339410) {
                break;
            }
        }
        for (int i365 = 106; i365 < 118; i365++) {
            bArr[363] = (byte) i365;
            if (sha1_32(bArr) == -1904485740) {
                break;
            }
        }
        for (int i366 = -53; i366 < -28; i366++) {
            bArr[364] = (byte) i366;
            if (sha1_32(bArr) == 2044483069) {
                break;
            }
        }
        for (int i367 = -63; i367 < -49; i367++) {
            bArr[365] = (byte) i367;
            if (sha1_32(bArr) == -490006531) {
                break;
            }
        }
        for (int i368 = -65; i368 < -54; i368++) {
            bArr[366] = (byte) i368;
            if (sha1_32(bArr) == 1265029016) {
                break;
            }
        }
        for (int i369 = 36; i369 < 52; i369++) {
            bArr[367] = (byte) i369;
            if (sha1_32(bArr) == -1932959921) {
                break;
            }
        }
        for (int i370 = -24; i370 < -3; i370++) {
            bArr[368] = (byte) i370;
            if (sha1_32(bArr) == -1841969655) {
                break;
            }
        }
        for (int i371 = 41; i371 < 63; i371++) {
            bArr[369] = (byte) i371;
            if (sha1_32(bArr) == -692645093) {
                break;
            }
        }
        for (int i372 = -128; i372 < -113; i372++) {
            bArr[370] = (byte) i372;
            if (sha1_32(bArr) == -1643916451) {
                break;
            }
        }
        for (int i373 = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE; i373 < 116; i373++) {
            bArr[371] = (byte) i373;
            if (sha1_32(bArr) == 1640631402) {
                break;
            }
        }
        for (int i374 = -107; i374 < -95; i374++) {
            bArr[372] = (byte) i374;
            if (sha1_32(bArr) == -1218159056) {
                break;
            }
        }
        for (int i375 = 10; i375 < 19; i375++) {
            bArr[373] = (byte) i375;
            if (sha1_32(bArr) == 43541867) {
                break;
            }
        }
        for (int i376 = 4; i376 < 11; i376++) {
            bArr[374] = (byte) i376;
            if (sha1_32(bArr) == 1755750116) {
                break;
            }
        }
        for (int i377 = 62; i377 < 69; i377++) {
            bArr[375] = (byte) i377;
            if (sha1_32(bArr) == -1802676719) {
                break;
            }
        }
        for (int i378 = 75; i378 < 96; i378++) {
            bArr[376] = (byte) i378;
            if (sha1_32(bArr) == 67690236) {
                break;
            }
        }
        for (int i379 = -90; i379 < -77; i379++) {
            bArr[377] = (byte) i379;
            if (sha1_32(bArr) == -496732558) {
                break;
            }
        }
        for (int i380 = -86; i380 < -81; i380++) {
            bArr[378] = (byte) i380;
            if (sha1_32(bArr) == 70521152) {
                break;
            }
        }
        for (int i381 = 99; i381 < 116; i381++) {
            bArr[379] = (byte) i381;
            if (sha1_32(bArr) == 1376127832) {
                break;
            }
        }
        for (int i382 = 51; i382 < 81; i382++) {
            bArr[380] = (byte) i382;
            if (sha1_32(bArr) == 819916488) {
                break;
            }
        }
        for (int i383 = -4; i383 < 15; i383++) {
            bArr[381] = (byte) i383;
            if (sha1_32(bArr) == -1281312292) {
                break;
            }
        }
        for (int i384 = 85; i384 < 107; i384++) {
            bArr[382] = (byte) i384;
            if (sha1_32(bArr) == 1291835275) {
                break;
            }
        }
        for (int i385 = -46; i385 < -38; i385++) {
            bArr[383] = (byte) i385;
            if (sha1_32(bArr) == -1870288154) {
                break;
            }
        }
        for (int i386 = -73; i386 < -58; i386++) {
            bArr[384] = (byte) i386;
            if (sha1_32(bArr) == -160360516) {
                break;
            }
        }
        for (int i387 = -88; i387 < -65; i387++) {
            bArr[385] = (byte) i387;
            if (sha1_32(bArr) == 701629081) {
                break;
            }
        }
        for (int i388 = 89; i388 < 105; i388++) {
            bArr[386] = (byte) i388;
            if (sha1_32(bArr) == -272055204) {
                break;
            }
        }
        for (int i389 = 33; i389 < 58; i389++) {
            bArr[387] = (byte) i389;
            if (sha1_32(bArr) == -1457951649) {
                break;
            }
        }
        for (int i390 = -115; i390 < -90; i390++) {
            bArr[388] = (byte) i390;
            if (sha1_32(bArr) == 401939930) {
                break;
            }
        }
        for (int i391 = -35; i391 < -4; i391++) {
            bArr[389] = (byte) i391;
            if (sha1_32(bArr) == 1260663094) {
                break;
            }
        }
        for (int i392 = 83; i392 < 106; i392++) {
            bArr[390] = (byte) i392;
            if (sha1_32(bArr) == 1074425815) {
                break;
            }
        }
        for (int i393 = -22; i393 < -7; i393++) {
            bArr[391] = (byte) i393;
            if (sha1_32(bArr) == -1433870633) {
                break;
            }
        }
        for (int i394 = -105; i394 < -98; i394++) {
            bArr[392] = (byte) i394;
            if (sha1_32(bArr) == 331219086) {
                break;
            }
        }
        for (int i395 = -18; i395 < 7; i395++) {
            bArr[393] = (byte) i395;
            if (sha1_32(bArr) == -739261771) {
                break;
            }
        }
        for (int i396 = -100; i396 < -87; i396++) {
            bArr[394] = (byte) i396;
            if (sha1_32(bArr) == -1765248120) {
                break;
            }
        }
        for (int i397 = -85; i397 < -66; i397++) {
            bArr[395] = (byte) i397;
            if (sha1_32(bArr) == -262421876) {
                break;
            }
        }
        for (int i398 = 119; i398 < 128; i398++) {
            bArr[396] = (byte) i398;
            if (sha1_32(bArr) == 85600932) {
                break;
            }
        }
        for (int i399 = 119; i399 < 124; i399++) {
            bArr[397] = (byte) i399;
            if (sha1_32(bArr) == 721960508) {
                break;
            }
        }
        for (int i400 = 102; i400 < 124; i400++) {
            bArr[398] = (byte) i400;
            if (sha1_32(bArr) == 1700095527) {
                break;
            }
        }
        for (int i401 = -125; i401 < -104; i401++) {
            bArr[399] = (byte) i401;
            if (sha1_32(bArr) == 1707672793) {
                break;
            }
        }
        for (int i402 = 20; i402 < 34; i402++) {
            bArr[400] = (byte) i402;
            if (sha1_32(bArr) == 968359534) {
                break;
            }
        }
        for (int i403 = 115; i403 < 128; i403++) {
            bArr[401] = (byte) i403;
            if (sha1_32(bArr) == -1376305522) {
                break;
            }
        }
        for (int i404 = -119; i404 < -105; i404++) {
            bArr[402] = (byte) i404;
            if (sha1_32(bArr) == 1915307012) {
                break;
            }
        }
        for (int i405 = -37; i405 < -20; i405++) {
            bArr[403] = (byte) i405;
            if (sha1_32(bArr) == -1400143527) {
                break;
            }
        }
        for (int i406 = -38; i406 < -13; i406++) {
            bArr[404] = (byte) i406;
            if (sha1_32(bArr) == -519811307) {
                break;
            }
        }
        for (int i407 = 110; i407 < 128; i407++) {
            bArr[405] = (byte) i407;
            if (sha1_32(bArr) == -1728438551) {
                break;
            }
        }
        for (int i408 = 35; i408 < 54; i408++) {
            bArr[406] = (byte) i408;
            if (sha1_32(bArr) == 1305171424) {
                break;
            }
        }
        for (int i409 = -57; i409 < -51; i409++) {
            bArr[407] = (byte) i409;
            if (sha1_32(bArr) == 1309925278) {
                break;
            }
        }
        for (int i410 = 3; i410 < 22; i410++) {
            bArr[408] = (byte) i410;
            if (sha1_32(bArr) == -1817046702) {
                break;
            }
        }
        for (int i411 = 88; i411 < 94; i411++) {
            bArr[409] = (byte) i411;
            if (sha1_32(bArr) == 463741669) {
                break;
            }
        }
        for (int i412 = 74; i412 < 96; i412++) {
            bArr[410] = (byte) i412;
            if (sha1_32(bArr) == -1930578557) {
                break;
            }
        }
        for (int i413 = -128; i413 < -122; i413++) {
            bArr[411] = (byte) i413;
            if (sha1_32(bArr) == -170544972) {
                break;
            }
        }
        for (int i414 = 53; i414 < 58; i414++) {
            bArr[412] = (byte) i414;
            if (sha1_32(bArr) == 469494994) {
                break;
            }
        }
        for (int i415 = 71; i415 < 79; i415++) {
            bArr[413] = (byte) i415;
            if (sha1_32(bArr) == 132718373) {
                break;
            }
        }
        for (int i416 = -88; i416 < -73; i416++) {
            bArr[414] = (byte) i416;
            if (sha1_32(bArr) == -1454234958) {
                break;
            }
        }
        for (int i417 = -82; i417 < -64; i417++) {
            bArr[415] = (byte) i417;
            if (sha1_32(bArr) == -704771930) {
                break;
            }
        }
        for (int i418 = -108; i418 < -79; i418++) {
            bArr[416] = (byte) i418;
            if (sha1_32(bArr) == -590909818) {
                break;
            }
        }
        for (int i419 = -110; i419 < -90; i419++) {
            bArr[417] = (byte) i419;
            if (sha1_32(bArr) == -1500900665) {
                break;
            }
        }
        for (int i420 = 63; i420 < 81; i420++) {
            bArr[418] = (byte) i420;
            if (sha1_32(bArr) == 893687443) {
                break;
            }
        }
        for (int i421 = 63; i421 < 77; i421++) {
            bArr[419] = (byte) i421;
            if (sha1_32(bArr) == -349937451) {
                break;
            }
        }
        for (int i422 = -76; i422 < -52; i422++) {
            bArr[420] = (byte) i422;
            if (sha1_32(bArr) == 514534627) {
                break;
            }
        }
        for (int i423 = 90; i423 < 114; i423++) {
            bArr[421] = (byte) i423;
            if (sha1_32(bArr) == 1506437552) {
                break;
            }
        }
        for (int i424 = 0; i424 < 3; i424++) {
            bArr[422] = (byte) i424;
            if (sha1_32(bArr) == -42016749) {
                break;
            }
        }
        for (int i425 = -25; i425 < -6; i425++) {
            bArr[423] = (byte) i425;
            if (sha1_32(bArr) == -1720317467) {
                break;
            }
        }
        for (int i426 = -72; i426 < -58; i426++) {
            bArr[424] = (byte) i426;
            if (sha1_32(bArr) == 1260120975) {
                break;
            }
        }
        for (int i427 = -87; i427 < -64; i427++) {
            bArr[425] = (byte) i427;
            if (sha1_32(bArr) == 515397271) {
                break;
            }
        }
        for (int i428 = 34; i428 < 54; i428++) {
            bArr[426] = (byte) i428;
            if (sha1_32(bArr) == -1614948263) {
                break;
            }
        }
        for (int i429 = 79; i429 < 91; i429++) {
            bArr[427] = (byte) i429;
            if (sha1_32(bArr) == -1791582786) {
                break;
            }
        }
        for (int i430 = 35; i430 < 52; i430++) {
            bArr[428] = (byte) i430;
            if (sha1_32(bArr) == 1090879316) {
                break;
            }
        }
        for (int i431 = 34; i431 < 46; i431++) {
            bArr[429] = (byte) i431;
            if (sha1_32(bArr) == 1344453123) {
                break;
            }
        }
        for (int i432 = 111; i432 < 126; i432++) {
            bArr[430] = (byte) i432;
            if (sha1_32(bArr) == 2130609703) {
                break;
            }
        }
        for (int i433 = 37; i433 < 58; i433++) {
            bArr[431] = (byte) i433;
            if (sha1_32(bArr) == 781961318) {
                break;
            }
        }
        for (int i434 = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE; i434 < 108; i434++) {
            bArr[432] = (byte) i434;
            if (sha1_32(bArr) == 552239927) {
                break;
            }
        }
        for (int i435 = -47; i435 < -32; i435++) {
            bArr[433] = (byte) i435;
            if (sha1_32(bArr) == -763685264) {
                break;
            }
        }
        for (int i436 = -32; i436 < -16; i436++) {
            bArr[434] = (byte) i436;
            if (sha1_32(bArr) == 452750013) {
                break;
            }
        }
        for (int i437 = -49; i437 < -25; i437++) {
            bArr[435] = (byte) i437;
            if (sha1_32(bArr) == -1401033919) {
                break;
            }
        }
        for (int i438 = -116; i438 < -89; i438++) {
            bArr[436] = (byte) i438;
            if (sha1_32(bArr) == 1165838221) {
                break;
            }
        }
        for (int i439 = -20; i439 < -9; i439++) {
            bArr[437] = (byte) i439;
            if (sha1_32(bArr) == -2095146157) {
                break;
            }
        }
        for (int i440 = 34; i440 < 41; i440++) {
            bArr[438] = (byte) i440;
            if (sha1_32(bArr) == 1575685936) {
                break;
            }
        }
        for (int i441 = -86; i441 < -80; i441++) {
            bArr[439] = (byte) i441;
            if (sha1_32(bArr) == -2124160282) {
                break;
            }
        }
        for (int i442 = -39; i442 < -22; i442++) {
            bArr[440] = (byte) i442;
            if (sha1_32(bArr) == 1432783908) {
                break;
            }
        }
        for (int i443 = 101; i443 < 113; i443++) {
            bArr[441] = (byte) i443;
            if (sha1_32(bArr) == -1931053246) {
                break;
            }
        }
        for (int i444 = 94; i444 < 111; i444++) {
            bArr[442] = (byte) i444;
            if (sha1_32(bArr) == -1994622897) {
                break;
            }
        }
        for (int i445 = 49; i445 < 68; i445++) {
            bArr[443] = (byte) i445;
            if (sha1_32(bArr) == -737410251) {
                break;
            }
        }
        for (int i446 = -17; i446 < 6; i446++) {
            bArr[444] = (byte) i446;
            if (sha1_32(bArr) == 1577646444) {
                break;
            }
        }
        for (int i447 = -85; i447 < -61; i447++) {
            bArr[445] = (byte) i447;
            if (sha1_32(bArr) == -2080391622) {
                break;
            }
        }
        for (int i448 = -99; i448 < -80; i448++) {
            bArr[446] = (byte) i448;
            if (sha1_32(bArr) == -1155512475) {
                break;
            }
        }
        for (int i449 = 46; i449 < 54; i449++) {
            bArr[447] = (byte) i449;
            if (sha1_32(bArr) == 58144016) {
                break;
            }
        }
        for (int i450 = 2; i450 < 9; i450++) {
            bArr[448] = (byte) i450;
            if (sha1_32(bArr) == 359542887) {
                break;
            }
        }
        for (int i451 = -96; i451 < -79; i451++) {
            bArr[449] = (byte) i451;
            if (sha1_32(bArr) == -282552840) {
                break;
            }
        }
        for (int i452 = 1; i452 < 18; i452++) {
            bArr[450] = (byte) i452;
            if (sha1_32(bArr) == -6940561) {
                break;
            }
        }
        for (int i453 = 58; i453 < 78; i453++) {
            bArr[451] = (byte) i453;
            if (sha1_32(bArr) == -1771496702) {
                break;
            }
        }
        for (int i454 = 18; i454 < 29; i454++) {
            bArr[452] = (byte) i454;
            if (sha1_32(bArr) == -2082889083) {
                break;
            }
        }
        for (int i455 = 73; i455 < 85; i455++) {
            bArr[453] = (byte) i455;
            if (sha1_32(bArr) == -407600358) {
                break;
            }
        }
        for (int i456 = -126; i456 < -98; i456++) {
            bArr[454] = (byte) i456;
            if (sha1_32(bArr) == -1916243087) {
                break;
            }
        }
        for (int i457 = 22; i457 < 41; i457++) {
            bArr[455] = (byte) i457;
            if (sha1_32(bArr) == 618835751) {
                break;
            }
        }
        for (int i458 = -65; i458 < -52; i458++) {
            bArr[456] = (byte) i458;
            if (sha1_32(bArr) == 1401320681) {
                break;
            }
        }
        for (int i459 = 26; i459 < 29; i459++) {
            bArr[457] = (byte) i459;
            if (sha1_32(bArr) == 855913066) {
                break;
            }
        }
        for (int i460 = 22; i460 < 36; i460++) {
            bArr[458] = (byte) i460;
            if (sha1_32(bArr) == 1953144835) {
                break;
            }
        }
        for (int i461 = 22; i461 < 27; i461++) {
            bArr[459] = (byte) i461;
            if (sha1_32(bArr) == -726176086) {
                break;
            }
        }
        for (int i462 = 102; i462 < 112; i462++) {
            bArr[460] = (byte) i462;
            if (sha1_32(bArr) == -2102080023) {
                break;
            }
        }
        for (int i463 = 63; i463 < 83; i463++) {
            bArr[461] = (byte) i463;
            if (sha1_32(bArr) == -1209855468) {
                break;
            }
        }
        for (int i464 = -128; i464 < -126; i464++) {
            bArr[462] = (byte) i464;
            if (sha1_32(bArr) == -1030865026) {
                break;
            }
        }
        for (int i465 = -68; i465 < -54; i465++) {
            bArr[463] = (byte) i465;
            if (sha1_32(bArr) == -1086109143) {
                break;
            }
        }
        for (int i466 = -77; i466 < -59; i466++) {
            bArr[464] = (byte) i466;
            if (sha1_32(bArr) == -710668505) {
                break;
            }
        }
        for (int i467 = 7; i467 < 18; i467++) {
            bArr[465] = (byte) i467;
            if (sha1_32(bArr) == 39958151) {
                break;
            }
        }
        for (int i468 = 81; i468 < 84; i468++) {
            bArr[466] = (byte) i468;
            if (sha1_32(bArr) == 753969140) {
                break;
            }
        }
        for (int i469 = -14; i469 < 10; i469++) {
            bArr[467] = (byte) i469;
            if (sha1_32(bArr) == 301200036) {
                break;
            }
        }
        for (int i470 = 9; i470 < 24; i470++) {
            bArr[468] = (byte) i470;
            if (sha1_32(bArr) == -971019327) {
                break;
            }
        }
        for (int i471 = 118; i471 < 128; i471++) {
            bArr[469] = (byte) i471;
            if (sha1_32(bArr) == -398676344) {
                break;
            }
        }
        for (int i472 = 102; i472 < 128; i472++) {
            bArr[470] = (byte) i472;
            if (sha1_32(bArr) == 1587713415) {
                break;
            }
        }
        for (int i473 = DNPPhotoPrint.OVERCOAT_FINISH_PMATTE12; i473 < 128; i473++) {
            bArr[471] = (byte) i473;
            if (sha1_32(bArr) == 726761097) {
                break;
            }
        }
        for (int i474 = -71; i474 < -62; i474++) {
            bArr[472] = (byte) i474;
            if (sha1_32(bArr) == 563106845) {
                break;
            }
        }
        for (int i475 = -16; i475 < 5; i475++) {
            bArr[473] = (byte) i475;
            if (sha1_32(bArr) == 262535184) {
                break;
            }
        }
        for (int i476 = -84; i476 < -69; i476++) {
            bArr[474] = (byte) i476;
            if (sha1_32(bArr) == 1620286556) {
                break;
            }
        }
        for (int i477 = -95; i477 < -92; i477++) {
            bArr[475] = (byte) i477;
            if (sha1_32(bArr) == -1029564111) {
                break;
            }
        }
        for (int i478 = 106; i478 < 126; i478++) {
            bArr[476] = (byte) i478;
            if (sha1_32(bArr) == 470741529) {
                break;
            }
        }
        for (int i479 = -38; i479 < -26; i479++) {
            bArr[477] = (byte) i479;
            if (sha1_32(bArr) == -1235044619) {
                break;
            }
        }
        for (int i480 = 64; i480 < 81; i480++) {
            bArr[478] = (byte) i480;
            if (sha1_32(bArr) == 1422009883) {
                break;
            }
        }
        for (int i481 = -50; i481 < -24; i481++) {
            bArr[479] = (byte) i481;
            if (sha1_32(bArr) == -648275652) {
                break;
            }
        }
        for (int i482 = -32; i482 < -15; i482++) {
            bArr[480] = (byte) i482;
            if (sha1_32(bArr) == -114601340) {
                break;
            }
        }
        for (int i483 = 103; i483 < 122; i483++) {
            bArr[481] = (byte) i483;
            if (sha1_32(bArr) == -1813646761) {
                break;
            }
        }
        for (int i484 = ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT; i484 < 111; i484++) {
            bArr[482] = (byte) i484;
            if (sha1_32(bArr) == -827169029) {
                break;
            }
        }
        for (int i485 = 4; i485 < 25; i485++) {
            bArr[483] = (byte) i485;
            if (sha1_32(bArr) == -1599752079) {
                break;
            }
        }
        for (int i486 = 85; i486 < 103; i486++) {
            bArr[484] = (byte) i486;
            if (sha1_32(bArr) == -414435254) {
                break;
            }
        }
        for (int i487 = 34; i487 < 46; i487++) {
            bArr[485] = (byte) i487;
            if (sha1_32(bArr) == 2144645485) {
                break;
            }
        }
        for (int i488 = -23; i488 < -16; i488++) {
            bArr[486] = (byte) i488;
            if (sha1_32(bArr) == -1598744865) {
                break;
            }
        }
        for (int i489 = 64; i489 < 91; i489++) {
            bArr[487] = (byte) i489;
            if (sha1_32(bArr) == -1634691621) {
                break;
            }
        }
        for (int i490 = -4; i490 < 3; i490++) {
            bArr[488] = (byte) i490;
            if (sha1_32(bArr) == 123645535) {
                break;
            }
        }
        for (int i491 = 16; i491 < 33; i491++) {
            bArr[489] = (byte) i491;
            if (sha1_32(bArr) == 1045638196) {
                break;
            }
        }
        for (int i492 = -108; i492 < -103; i492++) {
            bArr[490] = (byte) i492;
            if (sha1_32(bArr) == -712857695) {
                break;
            }
        }
        for (int i493 = -110; i493 < -86; i493++) {
            bArr[491] = (byte) i493;
            if (sha1_32(bArr) == 263548274) {
                break;
            }
        }
        for (int i494 = 78; i494 < 88; i494++) {
            bArr[492] = (byte) i494;
            if (sha1_32(bArr) == 170997048) {
                break;
            }
        }
        for (int i495 = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE; i495 < 114; i495++) {
            bArr[493] = (byte) i495;
            if (sha1_32(bArr) == 1472792588) {
                break;
            }
        }
        for (int i496 = 98; i496 < 124; i496++) {
            bArr[494] = (byte) i496;
            if (sha1_32(bArr) == -154048718) {
                break;
            }
        }
        for (int i497 = 96; i497 < 101; i497++) {
            bArr[495] = (byte) i497;
            if (sha1_32(bArr) == -865877890) {
                break;
            }
        }
        for (int i498 = 49; i498 < 65; i498++) {
            bArr[496] = (byte) i498;
            if (sha1_32(bArr) == -1803168221) {
                break;
            }
        }
        for (int i499 = ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT; i499 < 121; i499++) {
            bArr[497] = (byte) i499;
            if (sha1_32(bArr) == 572433356) {
                break;
            }
        }
        for (int i500 = 103; i500 < 126; i500++) {
            bArr[498] = (byte) i500;
            if (sha1_32(bArr) == 1811475850) {
                break;
            }
        }
        for (int i501 = 93; i501 < 109; i501++) {
            bArr[499] = (byte) i501;
            if (sha1_32(bArr) == -597880809) {
                break;
            }
        }
        for (int i502 = UiPosIndexEnum.HOME_REPLACE_BG_TAB; i502 < 128; i502++) {
            bArr[500] = (byte) i502;
            if (sha1_32(bArr) == 1038518224) {
                break;
            }
        }
        for (int i503 = -29; i503 < -3; i503++) {
            bArr[501] = (byte) i503;
            if (sha1_32(bArr) == -445289098) {
                break;
            }
        }
        for (int i504 = -42; i504 < -21; i504++) {
            bArr[502] = (byte) i504;
            if (sha1_32(bArr) == 1530720686) {
                break;
            }
        }
        for (int i505 = -75; i505 < -71; i505++) {
            bArr[503] = (byte) i505;
            if (sha1_32(bArr) == 1157784632) {
                break;
            }
        }
        for (int i506 = 4; i506 < 31; i506++) {
            bArr[504] = (byte) i506;
            if (sha1_32(bArr) == 712370218) {
                break;
            }
        }
        for (int i507 = -50; i507 < -30; i507++) {
            bArr[505] = (byte) i507;
            if (sha1_32(bArr) == -1957119250) {
                break;
            }
        }
        for (int i508 = -16; i508 < -1; i508++) {
            bArr[506] = (byte) i508;
            if (sha1_32(bArr) == -752588553) {
                break;
            }
        }
        for (int i509 = -110; i509 < -96; i509++) {
            bArr[507] = (byte) i509;
            if (sha1_32(bArr) == -165310495) {
                break;
            }
        }
        for (int i510 = -94; i510 < -86; i510++) {
            bArr[508] = (byte) i510;
            if (sha1_32(bArr) == 1781806850) {
                break;
            }
        }
        for (int i511 = -57; i511 < -30; i511++) {
            bArr[509] = (byte) i511;
            if (sha1_32(bArr) == 1203712552) {
                break;
            }
        }
        for (int i512 = -122; i512 < -112; i512++) {
            bArr[510] = (byte) i512;
            if (sha1_32(bArr) == -715439676) {
                break;
            }
        }
        for (int i513 = -104; i513 < -90; i513++) {
            bArr[511] = (byte) i513;
            if (sha1_32(bArr) == 1990813250) {
                break;
            }
        }
        for (int i514 = -93; i514 < -79; i514++) {
            bArr[512] = (byte) i514;
            if (sha1_32(bArr) == 663260492) {
                break;
            }
        }
        for (int i515 = 44; i515 < 48; i515++) {
            bArr[513] = (byte) i515;
            if (sha1_32(bArr) == -239611820) {
                break;
            }
        }
        for (int i516 = -32; i516 < -14; i516++) {
            bArr[514] = (byte) i516;
            if (sha1_32(bArr) == -1599751866) {
                break;
            }
        }
        for (int i517 = -128; i517 < -119; i517++) {
            bArr[515] = (byte) i517;
            if (sha1_32(bArr) == -20043937) {
                break;
            }
        }
        for (int i518 = 116; i518 < 128; i518++) {
            bArr[516] = (byte) i518;
            if (sha1_32(bArr) == -1225429798) {
                break;
            }
        }
        for (int i519 = 14; i519 < 25; i519++) {
            bArr[517] = (byte) i519;
            if (sha1_32(bArr) == -2029512701) {
                break;
            }
        }
        for (int i520 = -103; i520 < -88; i520++) {
            bArr[518] = (byte) i520;
            if (sha1_32(bArr) == -1212945885) {
                break;
            }
        }
        for (int i521 = -96; i521 < -92; i521++) {
            bArr[519] = (byte) i521;
            if (sha1_32(bArr) == -1126620891) {
                break;
            }
        }
        for (int i522 = 47; i522 < 53; i522++) {
            bArr[520] = (byte) i522;
            if (sha1_32(bArr) == -1941044669) {
                break;
            }
        }
        for (int i523 = -13; i523 < 9; i523++) {
            bArr[521] = (byte) i523;
            if (sha1_32(bArr) == -85160841) {
                break;
            }
        }
        for (int i524 = -73; i524 < -56; i524++) {
            bArr[522] = (byte) i524;
            if (sha1_32(bArr) == 1130456622) {
                break;
            }
        }
        for (int i525 = -72; i525 < -69; i525++) {
            bArr[523] = (byte) i525;
            if (sha1_32(bArr) == -1230460875) {
                break;
            }
        }
        for (int i526 = 63; i526 < 77; i526++) {
            bArr[524] = (byte) i526;
            if (sha1_32(bArr) == -1858784560) {
                break;
            }
        }
        for (int i527 = -111; i527 < -82; i527++) {
            bArr[525] = (byte) i527;
            if (sha1_32(bArr) == -200861841) {
                break;
            }
        }
        for (int i528 = -25; i528 < -5; i528++) {
            bArr[526] = (byte) i528;
            if (sha1_32(bArr) == 954162217) {
                break;
            }
        }
        for (int i529 = -3; i529 < 9; i529++) {
            bArr[527] = (byte) i529;
            if (sha1_32(bArr) == 577817868) {
                break;
            }
        }
        for (int i530 = 62; i530 < 73; i530++) {
            bArr[528] = (byte) i530;
            if (sha1_32(bArr) == 861825786) {
                break;
            }
        }
        for (int i531 = 93; i531 < 111; i531++) {
            bArr[529] = (byte) i531;
            if (sha1_32(bArr) == -1144722796) {
                break;
            }
        }
        for (int i532 = -57; i532 < -47; i532++) {
            bArr[530] = (byte) i532;
            if (sha1_32(bArr) == 280597434) {
                break;
            }
        }
        for (int i533 = 33; i533 < 50; i533++) {
            bArr[531] = (byte) i533;
            if (sha1_32(bArr) == 1614768329) {
                break;
            }
        }
        for (int i534 = -80; i534 < -73; i534++) {
            bArr[532] = (byte) i534;
            if (sha1_32(bArr) == -463053590) {
                break;
            }
        }
        for (int i535 = -127; i535 < -113; i535++) {
            bArr[533] = (byte) i535;
            if (sha1_32(bArr) == 157110815) {
                break;
            }
        }
        for (int i536 = -101; i536 < -80; i536++) {
            bArr[534] = (byte) i536;
            if (sha1_32(bArr) == 327173538) {
                break;
            }
        }
        for (int i537 = 118; i537 < 128; i537++) {
            bArr[535] = (byte) i537;
            if (sha1_32(bArr) == 1289549734) {
                break;
            }
        }
        for (int i538 = -118; i538 < -91; i538++) {
            bArr[536] = (byte) i538;
            if (sha1_32(bArr) == 19454401) {
                break;
            }
        }
        for (int i539 = -118; i539 < -112; i539++) {
            bArr[537] = (byte) i539;
            if (sha1_32(bArr) == -639691112) {
                break;
            }
        }
        for (int i540 = 52; i540 < 80; i540++) {
            bArr[538] = (byte) i540;
            if (sha1_32(bArr) == 648566513) {
                break;
            }
        }
        for (int i541 = -58; i541 < -34; i541++) {
            bArr[539] = (byte) i541;
            if (sha1_32(bArr) == 1801016973) {
                break;
            }
        }
        for (int i542 = -126; i542 < -108; i542++) {
            bArr[540] = (byte) i542;
            if (sha1_32(bArr) == 1018429564) {
                break;
            }
        }
        for (int i543 = -108; i543 < -106; i543++) {
            bArr[541] = (byte) i543;
            if (sha1_32(bArr) == 475090610) {
                break;
            }
        }
        for (int i544 = 24; i544 < 26; i544++) {
            bArr[542] = (byte) i544;
            if (sha1_32(bArr) == -1334137682) {
                break;
            }
        }
        for (int i545 = -46; i545 < -30; i545++) {
            bArr[543] = (byte) i545;
            if (sha1_32(bArr) == 511960906) {
                break;
            }
        }
        for (int i546 = -55; i546 < -34; i546++) {
            bArr[544] = (byte) i546;
            if (sha1_32(bArr) == -562301396) {
                break;
            }
        }
        for (int i547 = 37; i547 < 47; i547++) {
            bArr[545] = (byte) i547;
            if (sha1_32(bArr) == -317494633) {
                break;
            }
        }
        for (int i548 = -51; i548 < -23; i548++) {
            bArr[546] = (byte) i548;
            if (sha1_32(bArr) == 1732381767) {
                break;
            }
        }
        for (int i549 = -83; i549 < -68; i549++) {
            bArr[547] = (byte) i549;
            if (sha1_32(bArr) == 1504347843) {
                break;
            }
        }
        for (int i550 = 94; i550 < 115; i550++) {
            bArr[548] = (byte) i550;
            if (sha1_32(bArr) == -1313363824) {
                break;
            }
        }
        for (int i551 = -71; i551 < -55; i551++) {
            bArr[549] = (byte) i551;
            if (sha1_32(bArr) == -2034023872) {
                break;
            }
        }
        for (int i552 = -108; i552 < -84; i552++) {
            bArr[550] = (byte) i552;
            if (sha1_32(bArr) == 1389569967) {
                break;
            }
        }
        for (int i553 = 83; i553 < 103; i553++) {
            bArr[551] = (byte) i553;
            if (sha1_32(bArr) == -2034135233) {
                break;
            }
        }
        for (int i554 = -19; i554 < -2; i554++) {
            bArr[552] = (byte) i554;
            if (sha1_32(bArr) == 1845623290) {
                break;
            }
        }
        for (int i555 = UiPosIndexEnum.HOME_COUNTDOWN; i555 < 128; i555++) {
            bArr[553] = (byte) i555;
            if (sha1_32(bArr) == -1994333856) {
                break;
            }
        }
        for (int i556 = -12; i556 < 11; i556++) {
            bArr[554] = (byte) i556;
            if (sha1_32(bArr) == 690425095) {
                break;
            }
        }
        for (int i557 = 28; i557 < 53; i557++) {
            bArr[555] = (byte) i557;
            if (sha1_32(bArr) == 904038130) {
                break;
            }
        }
        for (int i558 = 102; i558 < 112; i558++) {
            bArr[556] = (byte) i558;
            if (sha1_32(bArr) == -915497127) {
                break;
            }
        }
        for (int i559 = 110; i559 < 128; i559++) {
            bArr[557] = (byte) i559;
            if (sha1_32(bArr) == 1275235150) {
                break;
            }
        }
        for (int i560 = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE; i560 < 107; i560++) {
            bArr[558] = (byte) i560;
            if (sha1_32(bArr) == -949049675) {
                break;
            }
        }
        for (int i561 = -113; i561 < -110; i561++) {
            bArr[559] = (byte) i561;
            if (sha1_32(bArr) == 699292495) {
                break;
            }
        }
        for (int i562 = -43; i562 < -21; i562++) {
            bArr[560] = (byte) i562;
            if (sha1_32(bArr) == -1925510794) {
                break;
            }
        }
        for (int i563 = -106; i563 < -87; i563++) {
            bArr[561] = (byte) i563;
            if (sha1_32(bArr) == 653959286) {
                break;
            }
        }
        for (int i564 = -55; i564 < -42; i564++) {
            bArr[562] = (byte) i564;
            if (sha1_32(bArr) == 674387810) {
                break;
            }
        }
        for (int i565 = UiPosIndexEnum.HOME_REPLACE_BG_TAB; i565 < 128; i565++) {
            bArr[563] = (byte) i565;
            if (sha1_32(bArr) == -1960258882) {
                break;
            }
        }
        for (int i566 = 20; i566 < 40; i566++) {
            bArr[564] = (byte) i566;
            if (sha1_32(bArr) == 189667485) {
                break;
            }
        }
        for (int i567 = 68; i567 < 98; i567++) {
            bArr[565] = (byte) i567;
            if (sha1_32(bArr) == 2055469808) {
                break;
            }
        }
        for (int i568 = 74; i568 < 79; i568++) {
            bArr[566] = (byte) i568;
            if (sha1_32(bArr) == 1251382320) {
                break;
            }
        }
        for (int i569 = -107; i569 < -92; i569++) {
            bArr[567] = (byte) i569;
            if (sha1_32(bArr) == -1530795691) {
                break;
            }
        }
        for (int i570 = -76; i570 < -68; i570++) {
            bArr[568] = (byte) i570;
            if (sha1_32(bArr) == 275722226) {
                break;
            }
        }
        for (int i571 = -112; i571 < -90; i571++) {
            bArr[569] = (byte) i571;
            if (sha1_32(bArr) == 1731134650) {
                break;
            }
        }
        for (int i572 = -115; i572 < -96; i572++) {
            bArr[570] = (byte) i572;
            if (sha1_32(bArr) == 1916988995) {
                break;
            }
        }
        for (int i573 = 34; i573 < 61; i573++) {
            bArr[571] = (byte) i573;
            if (sha1_32(bArr) == -1038474585) {
                break;
            }
        }
        for (int i574 = 28; i574 < 49; i574++) {
            bArr[572] = (byte) i574;
            if (sha1_32(bArr) == 1485231717) {
                break;
            }
        }
        for (int i575 = UiPosIndexEnum.HOME_COUNTDOWN; i575 < 128; i575++) {
            bArr[573] = (byte) i575;
            if (sha1_32(bArr) == -2073196348) {
                break;
            }
        }
        for (int i576 = 78; i576 < 95; i576++) {
            bArr[574] = (byte) i576;
            if (sha1_32(bArr) == -660743399) {
                break;
            }
        }
        for (int i577 = 61; i577 < 79; i577++) {
            bArr[575] = (byte) i577;
            if (sha1_32(bArr) == 816004046) {
                break;
            }
        }
        for (int i578 = 7; i578 < 28; i578++) {
            bArr[576] = (byte) i578;
            if (sha1_32(bArr) == 1492707426) {
                break;
            }
        }
        for (int i579 = 16; i579 < 42; i579++) {
            bArr[577] = (byte) i579;
            if (sha1_32(bArr) == 1443146475) {
                break;
            }
        }
        for (int i580 = -120; i580 < -105; i580++) {
            bArr[578] = (byte) i580;
            if (sha1_32(bArr) == 1070875485) {
                break;
            }
        }
        for (int i581 = 1; i581 < 25; i581++) {
            bArr[579] = (byte) i581;
            if (sha1_32(bArr) == 208403776) {
                break;
            }
        }
        for (int i582 = -72; i582 < -54; i582++) {
            bArr[580] = (byte) i582;
            if (sha1_32(bArr) == -1264676723) {
                break;
            }
        }
        for (int i583 = -30; i583 < -26; i583++) {
            bArr[581] = (byte) i583;
            if (sha1_32(bArr) == -1906507047) {
                break;
            }
        }
        for (int i584 = 109; i584 < 126; i584++) {
            bArr[582] = (byte) i584;
            if (sha1_32(bArr) == -609959172) {
                break;
            }
        }
        for (int i585 = -118; i585 < -100; i585++) {
            bArr[583] = (byte) i585;
            if (sha1_32(bArr) == 1537339359) {
                break;
            }
        }
        for (int i586 = -9; i586 < 19; i586++) {
            bArr[584] = (byte) i586;
            if (sha1_32(bArr) == -1389396772) {
                break;
            }
        }
        for (int i587 = -37; i587 < -16; i587++) {
            bArr[585] = (byte) i587;
            if (sha1_32(bArr) == -1942424730) {
                break;
            }
        }
        for (int i588 = -94; i588 < -68; i588++) {
            bArr[586] = (byte) i588;
            if (sha1_32(bArr) == 946594103) {
                break;
            }
        }
        for (int i589 = 4; i589 < 7; i589++) {
            bArr[587] = (byte) i589;
            if (sha1_32(bArr) == -2133297013) {
                break;
            }
        }
        for (int i590 = -52; i590 < -35; i590++) {
            bArr[588] = (byte) i590;
            if (sha1_32(bArr) == 1449612953) {
                break;
            }
        }
        for (int i591 = -78; i591 < -59; i591++) {
            bArr[589] = (byte) i591;
            if (sha1_32(bArr) == 302923463) {
                break;
            }
        }
        for (int i592 = 40; i592 < 48; i592++) {
            bArr[590] = (byte) i592;
            if (sha1_32(bArr) == 90559646) {
                break;
            }
        }
        for (int i593 = 8; i593 < 28; i593++) {
            bArr[591] = (byte) i593;
            if (sha1_32(bArr) == -1280220924) {
                break;
            }
        }
        for (int i594 = -55; i594 < -42; i594++) {
            bArr[592] = (byte) i594;
            if (sha1_32(bArr) == 1532853228) {
                break;
            }
        }
        for (int i595 = 93; i595 < 113; i595++) {
            bArr[593] = (byte) i595;
            if (sha1_32(bArr) == -380640580) {
                break;
            }
        }
        for (int i596 = 100; i596 < 126; i596++) {
            bArr[594] = (byte) i596;
            if (sha1_32(bArr) == -1520140415) {
                break;
            }
        }
        for (int i597 = 100; i597 < 116; i597++) {
            bArr[595] = (byte) i597;
            if (sha1_32(bArr) == 1019506724) {
                break;
            }
        }
        for (int i598 = -121; i598 < -99; i598++) {
            bArr[596] = (byte) i598;
            if (sha1_32(bArr) == 1086869849) {
                break;
            }
        }
        for (int i599 = -63; i599 < -52; i599++) {
            bArr[597] = (byte) i599;
            if (sha1_32(bArr) == -1543526542) {
                break;
            }
        }
        for (int i600 = -70; i600 < -51; i600++) {
            bArr[598] = (byte) i600;
            if (sha1_32(bArr) == 1528181673) {
                break;
            }
        }
        for (int i601 = 89; i601 < 109; i601++) {
            bArr[599] = (byte) i601;
            if (sha1_32(bArr) == 1775065725) {
                break;
            }
        }
        for (int i602 = 67; i602 < 77; i602++) {
            bArr[600] = (byte) i602;
            if (sha1_32(bArr) == 1158724356) {
                break;
            }
        }
        for (int i603 = 45; i603 < 54; i603++) {
            bArr[601] = (byte) i603;
            if (sha1_32(bArr) == 1624274082) {
                break;
            }
        }
        for (int i604 = -107; i604 < -91; i604++) {
            bArr[602] = (byte) i604;
            if (sha1_32(bArr) == 1974576184) {
                break;
            }
        }
        for (int i605 = -25; i605 < -8; i605++) {
            bArr[603] = (byte) i605;
            if (sha1_32(bArr) == 1430817722) {
                break;
            }
        }
        for (int i606 = -17; i606 < 14; i606++) {
            bArr[604] = (byte) i606;
            if (sha1_32(bArr) == -2043781505) {
                break;
            }
        }
        for (int i607 = -48; i607 < -22; i607++) {
            bArr[605] = (byte) i607;
            if (sha1_32(bArr) == -1660696510) {
                break;
            }
        }
        for (int i608 = 22; i608 < 25; i608++) {
            bArr[606] = (byte) i608;
            if (sha1_32(bArr) == -397616807) {
                break;
            }
        }
        for (int i609 = -48; i609 < -28; i609++) {
            bArr[607] = (byte) i609;
            if (sha1_32(bArr) == -310719798) {
                break;
            }
        }
        for (int i610 = -88; i610 < -82; i610++) {
            bArr[608] = (byte) i610;
            if (sha1_32(bArr) == -1369229712) {
                break;
            }
        }
        for (int i611 = 8; i611 < 24; i611++) {
            bArr[609] = (byte) i611;
            if (sha1_32(bArr) == 1098220625) {
                break;
            }
        }
        for (int i612 = 1; i612 < 12; i612++) {
            bArr[610] = (byte) i612;
            if (sha1_32(bArr) == -481572675) {
                break;
            }
        }
        for (int i613 = -76; i613 < -55; i613++) {
            bArr[611] = (byte) i613;
            if (sha1_32(bArr) == 104902684) {
                break;
            }
        }
        for (int i614 = -35; i614 < -22; i614++) {
            bArr[612] = (byte) i614;
            if (sha1_32(bArr) == -1193147023) {
                break;
            }
        }
        for (int i615 = -125; i615 < -114; i615++) {
            bArr[613] = (byte) i615;
            if (sha1_32(bArr) == 58923272) {
                break;
            }
        }
        for (int i616 = 8; i616 < 17; i616++) {
            bArr[614] = (byte) i616;
            if (sha1_32(bArr) == 552568175) {
                break;
            }
        }
        for (int i617 = 74; i617 < 100; i617++) {
            bArr[615] = (byte) i617;
            if (sha1_32(bArr) == 556347277) {
                break;
            }
        }
        for (int i618 = 111; i618 < 118; i618++) {
            bArr[616] = (byte) i618;
            if (sha1_32(bArr) == -885736376) {
                break;
            }
        }
        for (int i619 = 8; i619 < 30; i619++) {
            bArr[617] = (byte) i619;
            if (sha1_32(bArr) == 306500796) {
                break;
            }
        }
        for (int i620 = 11; i620 < 23; i620++) {
            bArr[618] = (byte) i620;
            if (sha1_32(bArr) == 2061855945) {
                break;
            }
        }
        for (int i621 = -29; i621 < -18; i621++) {
            bArr[619] = (byte) i621;
            if (sha1_32(bArr) == 1430283412) {
                break;
            }
        }
        for (int i622 = -6; i622 < 9; i622++) {
            bArr[620] = (byte) i622;
            if (sha1_32(bArr) == -991931260) {
                break;
            }
        }
        for (int i623 = 98; i623 < 118; i623++) {
            bArr[621] = (byte) i623;
            if (sha1_32(bArr) == 1577307617) {
                break;
            }
        }
        for (int i624 = -105; i624 < -88; i624++) {
            bArr[622] = (byte) i624;
            if (sha1_32(bArr) == 2099441081) {
                break;
            }
        }
        for (int i625 = 31; i625 < 41; i625++) {
            bArr[623] = (byte) i625;
            if (sha1_32(bArr) == 1004123303) {
                break;
            }
        }
        for (int i626 = 75; i626 < 90; i626++) {
            bArr[624] = (byte) i626;
            if (sha1_32(bArr) == -1342756955) {
                break;
            }
        }
        for (int i627 = 43; i627 < 61; i627++) {
            bArr[625] = (byte) i627;
            if (sha1_32(bArr) == -686810739) {
                break;
            }
        }
        for (int i628 = 12; i628 < 35; i628++) {
            bArr[626] = (byte) i628;
            if (sha1_32(bArr) == -688935834) {
                break;
            }
        }
        for (int i629 = -16; i629 < 6; i629++) {
            bArr[627] = (byte) i629;
            if (sha1_32(bArr) == 1798337775) {
                break;
            }
        }
        for (int i630 = 62; i630 < 70; i630++) {
            bArr[628] = (byte) i630;
            if (sha1_32(bArr) == 518238114) {
                break;
            }
        }
        for (int i631 = -115; i631 < -97; i631++) {
            bArr[629] = (byte) i631;
            if (sha1_32(bArr) == -1008827568) {
                break;
            }
        }
        for (int i632 = -92; i632 < -82; i632++) {
            bArr[630] = (byte) i632;
            if (sha1_32(bArr) == -1232041221) {
                break;
            }
        }
        for (int i633 = -41; i633 < -12; i633++) {
            bArr[631] = (byte) i633;
            if (sha1_32(bArr) == -1069598905) {
                break;
            }
        }
        for (int i634 = 39; i634 < 56; i634++) {
            bArr[632] = (byte) i634;
            if (sha1_32(bArr) == -1197389025) {
                break;
            }
        }
        for (int i635 = -106; i635 < -91; i635++) {
            bArr[633] = (byte) i635;
            if (sha1_32(bArr) == -191934546) {
                break;
            }
        }
        for (int i636 = 79; i636 < 91; i636++) {
            bArr[634] = (byte) i636;
            if (sha1_32(bArr) == -1160306121) {
                break;
            }
        }
        for (int i637 = 70; i637 < 89; i637++) {
            bArr[635] = (byte) i637;
            if (sha1_32(bArr) == 1510191606) {
                break;
            }
        }
        for (int i638 = 27; i638 < 44; i638++) {
            bArr[636] = (byte) i638;
            if (sha1_32(bArr) == 1102105458) {
                break;
            }
        }
        for (int i639 = -58; i639 < -35; i639++) {
            bArr[637] = (byte) i639;
            if (sha1_32(bArr) == 1891972351) {
                break;
            }
        }
        for (int i640 = 92; i640 < 104; i640++) {
            bArr[638] = (byte) i640;
            if (sha1_32(bArr) == 1821453720) {
                break;
            }
        }
        for (int i641 = 49; i641 < 63; i641++) {
            bArr[639] = (byte) i641;
            if (sha1_32(bArr) == -1305817110) {
                break;
            }
        }
        for (int i642 = -44; i642 < -14; i642++) {
            bArr[640] = (byte) i642;
            if (sha1_32(bArr) == 508938960) {
                break;
            }
        }
        for (int i643 = -5; i643 < 12; i643++) {
            bArr[641] = (byte) i643;
            if (sha1_32(bArr) == 377777845) {
                break;
            }
        }
        for (int i644 = 15; i644 < 20; i644++) {
            bArr[642] = (byte) i644;
            if (sha1_32(bArr) == -777734807) {
                break;
            }
        }
        for (int i645 = -17; i645 < 10; i645++) {
            bArr[643] = (byte) i645;
            if (sha1_32(bArr) == 1765123707) {
                break;
            }
        }
        for (int i646 = -19; i646 < -12; i646++) {
            bArr[644] = (byte) i646;
            if (sha1_32(bArr) == 615856442) {
                break;
            }
        }
        for (int i647 = 0; i647 < 19; i647++) {
            bArr[645] = (byte) i647;
            if (sha1_32(bArr) == 1446518280) {
                break;
            }
        }
        for (int i648 = -33; i648 < -22; i648++) {
            bArr[646] = (byte) i648;
            if (sha1_32(bArr) == 1676628034) {
                break;
            }
        }
        for (int i649 = 13; i649 < 22; i649++) {
            bArr[647] = (byte) i649;
            if (sha1_32(bArr) == 1990364180) {
                break;
            }
        }
        for (int i650 = -52; i650 < -26; i650++) {
            bArr[648] = (byte) i650;
            if (sha1_32(bArr) == -286094353) {
                break;
            }
        }
        for (int i651 = 11; i651 < 23; i651++) {
            bArr[649] = (byte) i651;
            if (sha1_32(bArr) == 1635287789) {
                break;
            }
        }
        for (int i652 = 15; i652 < 34; i652++) {
            bArr[650] = (byte) i652;
            if (sha1_32(bArr) == -1360912766) {
                break;
            }
        }
        for (int i653 = 40; i653 < 61; i653++) {
            bArr[651] = (byte) i653;
            if (sha1_32(bArr) == -1881259368) {
                break;
            }
        }
        for (int i654 = -108; i654 < -102; i654++) {
            bArr[652] = (byte) i654;
            if (sha1_32(bArr) == -864609772) {
                break;
            }
        }
        for (int i655 = -126; i655 < -115; i655++) {
            bArr[653] = (byte) i655;
            if (sha1_32(bArr) == 1807084307) {
                break;
            }
        }
        for (int i656 = -44; i656 < -23; i656++) {
            bArr[654] = (byte) i656;
            if (sha1_32(bArr) == 1533897175) {
                break;
            }
        }
        for (int i657 = -115; i657 < -96; i657++) {
            bArr[655] = (byte) i657;
            if (sha1_32(bArr) == 1465139568) {
                break;
            }
        }
        for (int i658 = 56; i658 < 82; i658++) {
            bArr[656] = (byte) i658;
            if (sha1_32(bArr) == 1687513955) {
                break;
            }
        }
        for (int i659 = -79; i659 < -66; i659++) {
            bArr[657] = (byte) i659;
            if (sha1_32(bArr) == 1559711819) {
                break;
            }
        }
        for (int i660 = 8; i660 < 28; i660++) {
            bArr[658] = (byte) i660;
            if (sha1_32(bArr) == 1702769345) {
                break;
            }
        }
        bArr[659] = (byte) (-23);
        sha1_32(bArr);
        for (int i661 = -32; i661 < -16; i661++) {
            bArr[660] = (byte) i661;
            if (sha1_32(bArr) == -983874978) {
                break;
            }
        }
        for (int i662 = 117; i662 < 121; i662++) {
            bArr[661] = (byte) i662;
            if (sha1_32(bArr) == -1149309088) {
                break;
            }
        }
        for (int i663 = 85; i663 < 96; i663++) {
            bArr[662] = (byte) i663;
            if (sha1_32(bArr) == -1954515391) {
                break;
            }
        }
        for (int i664 = -67; i664 < -56; i664++) {
            bArr[663] = (byte) i664;
            if (sha1_32(bArr) == -307575824) {
                break;
            }
        }
        for (int i665 = -101; i665 < -74; i665++) {
            bArr[664] = (byte) i665;
            if (sha1_32(bArr) == 363968679) {
                break;
            }
        }
        for (int i666 = -100; i666 < -81; i666++) {
            bArr[665] = (byte) i666;
            if (sha1_32(bArr) == 882432333) {
                break;
            }
        }
        for (int i667 = -128; i667 < -120; i667++) {
            bArr[666] = (byte) i667;
            if (sha1_32(bArr) == 1344145936) {
                break;
            }
        }
        for (int i668 = -24; i668 < -6; i668++) {
            bArr[667] = (byte) i668;
            if (sha1_32(bArr) == -777252296) {
                break;
            }
        }
        for (int i669 = 19; i669 < 50; i669++) {
            bArr[668] = (byte) i669;
            if (sha1_32(bArr) == -95106103) {
                break;
            }
        }
        for (int i670 = -54; i670 < -40; i670++) {
            bArr[669] = (byte) i670;
            if (sha1_32(bArr) == 1025824571) {
                break;
            }
        }
        for (int i671 = 31; i671 < 47; i671++) {
            bArr[670] = (byte) i671;
            if (sha1_32(bArr) == 1107175776) {
                break;
            }
        }
        for (int i672 = -63; i672 < -44; i672++) {
            bArr[671] = (byte) i672;
            if (sha1_32(bArr) == 2113766029) {
                break;
            }
        }
        for (int i673 = -87; i673 < -68; i673++) {
            bArr[672] = (byte) i673;
            if (sha1_32(bArr) == -238217956) {
                break;
            }
        }
        for (int i674 = 80; i674 < 98; i674++) {
            bArr[673] = (byte) i674;
            if (sha1_32(bArr) == 398669940) {
                break;
            }
        }
        for (int i675 = -76; i675 < -63; i675++) {
            bArr[674] = (byte) i675;
            if (sha1_32(bArr) == 1716050615) {
                break;
            }
        }
        for (int i676 = 120; i676 < 127; i676++) {
            bArr[675] = (byte) i676;
            if (sha1_32(bArr) == -1634753519) {
                break;
            }
        }
        for (int i677 = 92; i677 < 111; i677++) {
            bArr[676] = (byte) i677;
            if (sha1_32(bArr) == 379143810) {
                break;
            }
        }
        for (int i678 = 3; i678 < 20; i678++) {
            bArr[677] = (byte) i678;
            if (sha1_32(bArr) == 1252815882) {
                break;
            }
        }
        for (int i679 = -35; i679 < -29; i679++) {
            bArr[678] = (byte) i679;
            if (sha1_32(bArr) == -1705393784) {
                break;
            }
        }
        for (int i680 = -66; i680 < -51; i680++) {
            bArr[679] = (byte) i680;
            if (sha1_32(bArr) == -1611047193) {
                break;
            }
        }
        for (int i681 = -125; i681 < -110; i681++) {
            bArr[680] = (byte) i681;
            if (sha1_32(bArr) == -1730805956) {
                break;
            }
        }
        for (int i682 = 1; i682 < 18; i682++) {
            bArr[681] = (byte) i682;
            if (sha1_32(bArr) == 1317680280) {
                break;
            }
        }
        for (int i683 = -3; i683 < 23; i683++) {
            bArr[682] = (byte) i683;
            if (sha1_32(bArr) == 1937884348) {
                break;
            }
        }
        for (int i684 = -23; i684 < 0; i684++) {
            bArr[683] = (byte) i684;
            if (sha1_32(bArr) == 884634884) {
                break;
            }
        }
        for (int i685 = -2; i685 < 19; i685++) {
            bArr[684] = (byte) i685;
            if (sha1_32(bArr) == 745824685) {
                break;
            }
        }
        for (int i686 = 49; i686 < 61; i686++) {
            bArr[685] = (byte) i686;
            if (sha1_32(bArr) == 1817312682) {
                break;
            }
        }
        for (int i687 = 30; i687 < 51; i687++) {
            bArr[686] = (byte) i687;
            if (sha1_32(bArr) == 1716411338) {
                break;
            }
        }
        for (int i688 = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE; i688 < 126; i688++) {
            bArr[687] = (byte) i688;
            if (sha1_32(bArr) == 573038477) {
                break;
            }
        }
        for (int i689 = 4; i689 < 33; i689++) {
            bArr[688] = (byte) i689;
            if (sha1_32(bArr) == -1610610089) {
                break;
            }
        }
        for (int i690 = -128; i690 < -121; i690++) {
            bArr[689] = (byte) i690;
            if (sha1_32(bArr) == 227943824) {
                break;
            }
        }
        for (int i691 = -48; i691 < -32; i691++) {
            bArr[690] = (byte) i691;
            if (sha1_32(bArr) == -2107557016) {
                break;
            }
        }
        for (int i692 = -92; i692 < -68; i692++) {
            bArr[691] = (byte) i692;
            if (sha1_32(bArr) == -713988964) {
                break;
            }
        }
        for (int i693 = -106; i693 < -92; i693++) {
            bArr[692] = (byte) i693;
            if (sha1_32(bArr) == 1016100559) {
                break;
            }
        }
        for (int i694 = 51; i694 < 67; i694++) {
            bArr[693] = (byte) i694;
            if (sha1_32(bArr) == -690000249) {
                break;
            }
        }
        for (int i695 = -110; i695 < -95; i695++) {
            bArr[694] = (byte) i695;
            if (sha1_32(bArr) == -1075383172) {
                break;
            }
        }
        for (int i696 = 34; i696 < 50; i696++) {
            bArr[695] = (byte) i696;
            if (sha1_32(bArr) == 804468576) {
                break;
            }
        }
        for (int i697 = -3; i697 < 17; i697++) {
            bArr[696] = (byte) i697;
            if (sha1_32(bArr) == -2007180076) {
                break;
            }
        }
        for (int i698 = 81; i698 < 97; i698++) {
            bArr[697] = (byte) i698;
            if (sha1_32(bArr) == 1531659836) {
                break;
            }
        }
        for (int i699 = 46; i699 < 74; i699++) {
            bArr[698] = (byte) i699;
            if (sha1_32(bArr) == -1451405116) {
                break;
            }
        }
        for (int i700 = -82; i700 < -68; i700++) {
            bArr[699] = (byte) i700;
            if (sha1_32(bArr) == 557779707) {
                break;
            }
        }
        for (int i701 = 7; i701 < 27; i701++) {
            bArr[700] = (byte) i701;
            if (sha1_32(bArr) == -1728008741) {
                break;
            }
        }
        for (int i702 = 65; i702 < 77; i702++) {
            bArr[701] = (byte) i702;
            if (sha1_32(bArr) == 1081236402) {
                break;
            }
        }
        for (int i703 = 42; i703 < 67; i703++) {
            bArr[702] = (byte) i703;
            if (sha1_32(bArr) == -478046744) {
                break;
            }
        }
        for (int i704 = 42; i704 < 52; i704++) {
            bArr[703] = (byte) i704;
            if (sha1_32(bArr) == 1887180653) {
                break;
            }
        }
        for (int i705 = 116; i705 < 127; i705++) {
            bArr[704] = (byte) i705;
            if (sha1_32(bArr) == -2086233891) {
                break;
            }
        }
        for (int i706 = -113; i706 < -94; i706++) {
            bArr[705] = (byte) i706;
            if (sha1_32(bArr) == -293954145) {
                break;
            }
        }
        for (int i707 = 3; i707 < 16; i707++) {
            bArr[706] = (byte) i707;
            if (sha1_32(bArr) == 983383751) {
                break;
            }
        }
        for (int i708 = -32; i708 < -20; i708++) {
            bArr[707] = (byte) i708;
            if (sha1_32(bArr) == 1443260618) {
                break;
            }
        }
        for (int i709 = -99; i709 < -78; i709++) {
            bArr[708] = (byte) i709;
            if (sha1_32(bArr) == 1211775911) {
                break;
            }
        }
        for (int i710 = -116; i710 < -105; i710++) {
            bArr[709] = (byte) i710;
            if (sha1_32(bArr) == 715662242) {
                break;
            }
        }
        for (int i711 = UiPosIndexEnum.HOME_REPLACE_BG_TAB; i711 < 124; i711++) {
            bArr[710] = (byte) i711;
            if (sha1_32(bArr) == 91978095) {
                break;
            }
        }
        for (int i712 = -6; i712 < 15; i712++) {
            bArr[711] = (byte) i712;
            if (sha1_32(bArr) == -1078570020) {
                break;
            }
        }
        for (int i713 = 93; i713 < 99; i713++) {
            bArr[712] = (byte) i713;
            if (sha1_32(bArr) == -1508104930) {
                break;
            }
        }
        for (int i714 = -54; i714 < -42; i714++) {
            bArr[713] = (byte) i714;
            if (sha1_32(bArr) == 1109035888) {
                break;
            }
        }
        for (int i715 = -122; i715 < -107; i715++) {
            bArr[714] = (byte) i715;
            if (sha1_32(bArr) == -1802679847) {
                break;
            }
        }
        for (int i716 = 102; i716 < 116; i716++) {
            bArr[715] = (byte) i716;
            if (sha1_32(bArr) == 617788099) {
                break;
            }
        }
        for (int i717 = -20; i717 < -2; i717++) {
            bArr[716] = (byte) i717;
            if (sha1_32(bArr) == -1289750387) {
                break;
            }
        }
        for (int i718 = -78; i718 < -62; i718++) {
            bArr[717] = (byte) i718;
            if (sha1_32(bArr) == -609814487) {
                break;
            }
        }
        for (int i719 = 20; i719 < 45; i719++) {
            bArr[718] = (byte) i719;
            if (sha1_32(bArr) == 1307795600) {
                break;
            }
        }
        for (int i720 = -45; i720 < -30; i720++) {
            bArr[719] = (byte) i720;
            if (sha1_32(bArr) == 1512374403) {
                break;
            }
        }
        for (int i721 = -99; i721 < -71; i721++) {
            bArr[720] = (byte) i721;
            if (sha1_32(bArr) == 1905862474) {
                break;
            }
        }
        for (int i722 = 45; i722 < 68; i722++) {
            bArr[721] = (byte) i722;
            if (sha1_32(bArr) == -248661721) {
                break;
            }
        }
        for (int i723 = 73; i723 < 83; i723++) {
            bArr[722] = (byte) i723;
            if (sha1_32(bArr) == -709734424) {
                break;
            }
        }
        for (int i724 = -86; i724 < -60; i724++) {
            bArr[723] = (byte) i724;
            if (sha1_32(bArr) == 226501006) {
                break;
            }
        }
        for (int i725 = -4; i725 < 19; i725++) {
            bArr[724] = (byte) i725;
            if (sha1_32(bArr) == 1252444419) {
                break;
            }
        }
        for (int i726 = 54; i726 < 65; i726++) {
            bArr[725] = (byte) i726;
            if (sha1_32(bArr) == -2146707134) {
                break;
            }
        }
        for (int i727 = 66; i727 < 88; i727++) {
            bArr[726] = (byte) i727;
            if (sha1_32(bArr) == -798879332) {
                break;
            }
        }
        for (int i728 = -74; i728 < -58; i728++) {
            bArr[727] = (byte) i728;
            if (sha1_32(bArr) == -39738236) {
                break;
            }
        }
        for (int i729 = ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT; i729 < 124; i729++) {
            bArr[728] = (byte) i729;
            if (sha1_32(bArr) == 2147245567) {
                break;
            }
        }
        for (int i730 = 100; i730 < 117; i730++) {
            bArr[729] = (byte) i730;
            if (sha1_32(bArr) == 1853221934) {
                break;
            }
        }
        for (int i731 = 46; i731 < 64; i731++) {
            bArr[730] = (byte) i731;
            if (sha1_32(bArr) == -15707996) {
                break;
            }
        }
        for (int i732 = 17; i732 < 29; i732++) {
            bArr[731] = (byte) i732;
            if (sha1_32(bArr) == -112396174) {
                break;
            }
        }
        for (int i733 = 114; i733 < 125; i733++) {
            bArr[732] = (byte) i733;
            if (sha1_32(bArr) == -1058565108) {
                break;
            }
        }
        for (int i734 = -91; i734 < -81; i734++) {
            bArr[733] = (byte) i734;
            if (sha1_32(bArr) == -694731338) {
                break;
            }
        }
        for (int i735 = -16; i735 < 6; i735++) {
            bArr[734] = (byte) i735;
            if (sha1_32(bArr) == -1448764777) {
                break;
            }
        }
        for (int i736 = 87; i736 < 97; i736++) {
            bArr[735] = (byte) i736;
            if (sha1_32(bArr) == -2141786862) {
                break;
            }
        }
        for (int i737 = 47; i737 < 69; i737++) {
            bArr[736] = (byte) i737;
            if (sha1_32(bArr) == -574153449) {
                break;
            }
        }
        for (int i738 = 79; i738 < 95; i738++) {
            bArr[737] = (byte) i738;
            if (sha1_32(bArr) == -1509369360) {
                break;
            }
        }
        for (int i739 = 25; i739 < 37; i739++) {
            bArr[738] = (byte) i739;
            if (sha1_32(bArr) == 487122206) {
                break;
            }
        }
        for (int i740 = -9; i740 < 10; i740++) {
            bArr[739] = (byte) i740;
            if (sha1_32(bArr) == 486202543) {
                break;
            }
        }
        for (int i741 = 36; i741 < 56; i741++) {
            bArr[740] = (byte) i741;
            if (sha1_32(bArr) == -71715395) {
                break;
            }
        }
        for (int i742 = -42; i742 < -30; i742++) {
            bArr[741] = (byte) i742;
            if (sha1_32(bArr) == -162776869) {
                break;
            }
        }
        for (int i743 = 88; i743 < 107; i743++) {
            bArr[742] = (byte) i743;
            if (sha1_32(bArr) == 617555734) {
                break;
            }
        }
        for (int i744 = -108; i744 < -101; i744++) {
            bArr[743] = (byte) i744;
            if (sha1_32(bArr) == -893089679) {
                break;
            }
        }
        for (int i745 = 47; i745 < 60; i745++) {
            bArr[744] = (byte) i745;
            if (sha1_32(bArr) == -1859078724) {
                break;
            }
        }
        for (int i746 = -79; i746 < -63; i746++) {
            bArr[745] = (byte) i746;
            if (sha1_32(bArr) == -1309080338) {
                break;
            }
        }
        for (int i747 = 94; i747 < 103; i747++) {
            bArr[746] = (byte) i747;
            if (sha1_32(bArr) == 1421308536) {
                break;
            }
        }
        for (int i748 = -67; i748 < -46; i748++) {
            bArr[747] = (byte) i748;
            if (sha1_32(bArr) == 1107021211) {
                break;
            }
        }
        for (int i749 = -16; i749 < 3; i749++) {
            bArr[748] = (byte) i749;
            if (sha1_32(bArr) == -1587337840) {
                break;
            }
        }
        for (int i750 = 43; i750 < 60; i750++) {
            bArr[749] = (byte) i750;
            if (sha1_32(bArr) == -54330831) {
                break;
            }
        }
        for (int i751 = 103; i751 < 109; i751++) {
            bArr[750] = (byte) i751;
            if (sha1_32(bArr) == -1424105890) {
                break;
            }
        }
        for (int i752 = 111; i752 < 126; i752++) {
            bArr[751] = (byte) i752;
            if (sha1_32(bArr) == 687520458) {
                break;
            }
        }
        for (int i753 = -62; i753 < -34; i753++) {
            bArr[752] = (byte) i753;
            if (sha1_32(bArr) == -1566717102) {
                break;
            }
        }
        for (int i754 = -128; i754 < -109; i754++) {
            bArr[753] = (byte) i754;
            if (sha1_32(bArr) == -105515405) {
                break;
            }
        }
        for (int i755 = -64; i755 < -43; i755++) {
            bArr[754] = (byte) i755;
            if (sha1_32(bArr) == -206371168) {
                break;
            }
        }
        for (int i756 = 12; i756 < 17; i756++) {
            bArr[755] = (byte) i756;
            if (sha1_32(bArr) == -1728711239) {
                break;
            }
        }
        for (int i757 = -28; i757 < -15; i757++) {
            bArr[756] = (byte) i757;
            if (sha1_32(bArr) == 1770261174) {
                break;
            }
        }
        for (int i758 = -38; i758 < -26; i758++) {
            bArr[757] = (byte) i758;
            if (sha1_32(bArr) == 585773946) {
                break;
            }
        }
        for (int i759 = 96; i759 < 104; i759++) {
            bArr[758] = (byte) i759;
            if (sha1_32(bArr) == 1613574272) {
                break;
            }
        }
        for (int i760 = -42; i760 < -29; i760++) {
            bArr[759] = (byte) i760;
            if (sha1_32(bArr) == -1227028829) {
                break;
            }
        }
        for (int i761 = 6; i761 < 15; i761++) {
            bArr[760] = (byte) i761;
            if (sha1_32(bArr) == -2060824286) {
                break;
            }
        }
        for (int i762 = -79; i762 < -71; i762++) {
            bArr[761] = (byte) i762;
            if (sha1_32(bArr) == 756785298) {
                break;
            }
        }
        for (int i763 = -75; i763 < -59; i763++) {
            bArr[762] = (byte) i763;
            if (sha1_32(bArr) == -1634473379) {
                break;
            }
        }
        for (int i764 = -120; i764 < -100; i764++) {
            bArr[763] = (byte) i764;
            if (sha1_32(bArr) == -837150576) {
                break;
            }
        }
        for (int i765 = -16; i765 < 6; i765++) {
            bArr[764] = (byte) i765;
            if (sha1_32(bArr) == 614293684) {
                break;
            }
        }
        for (int i766 = 35; i766 < 44; i766++) {
            bArr[765] = (byte) i766;
            if (sha1_32(bArr) == -1662803328) {
                break;
            }
        }
        for (int i767 = 38; i767 < 47; i767++) {
            bArr[766] = (byte) i767;
            if (sha1_32(bArr) == -478519745) {
                break;
            }
        }
        for (int i768 = 34; i768 < 46; i768++) {
            bArr[767] = (byte) i768;
            if (sha1_32(bArr) == 481221808) {
                break;
            }
        }
        for (int i769 = -11; i769 < -4; i769++) {
            bArr[768] = (byte) i769;
            if (sha1_32(bArr) == -1463820764) {
                break;
            }
        }
        for (int i770 = 89; i770 < 120; i770++) {
            bArr[769] = (byte) i770;
            if (sha1_32(bArr) == 1275851478) {
                break;
            }
        }
        for (int i771 = 65; i771 < 86; i771++) {
            bArr[770] = (byte) i771;
            if (sha1_32(bArr) == -505400692) {
                break;
            }
        }
        for (int i772 = 36; i772 < 58; i772++) {
            bArr[771] = (byte) i772;
            if (sha1_32(bArr) == -1022531783) {
                break;
            }
        }
        for (int i773 = -76; i773 < -68; i773++) {
            bArr[772] = (byte) i773;
            if (sha1_32(bArr) == 297333606) {
                break;
            }
        }
        for (int i774 = 47; i774 < 58; i774++) {
            bArr[773] = (byte) i774;
            if (sha1_32(bArr) == 318175348) {
                break;
            }
        }
        for (int i775 = -25; i775 < -16; i775++) {
            bArr[774] = (byte) i775;
            if (sha1_32(bArr) == 2059116404) {
                break;
            }
        }
        for (int i776 = 71; i776 < 82; i776++) {
            bArr[775] = (byte) i776;
            if (sha1_32(bArr) == -1401996978) {
                break;
            }
        }
        for (int i777 = -40; i777 < -31; i777++) {
            bArr[776] = (byte) i777;
            if (sha1_32(bArr) == 647272317) {
                break;
            }
        }
        for (int i778 = 43; i778 < 74; i778++) {
            bArr[777] = (byte) i778;
            if (sha1_32(bArr) == 508800283) {
                break;
            }
        }
        for (int i779 = 56; i779 < 67; i779++) {
            bArr[778] = (byte) i779;
            if (sha1_32(bArr) == -1760560370) {
                break;
            }
        }
        for (int i780 = -3; i780 < 18; i780++) {
            bArr[779] = (byte) i780;
            if (sha1_32(bArr) == -908399818) {
                break;
            }
        }
        for (int i781 = -21; i781 < -7; i781++) {
            bArr[780] = (byte) i781;
            if (sha1_32(bArr) == 2121976798) {
                break;
            }
        }
        for (int i782 = -19; i782 < -7; i782++) {
            bArr[781] = (byte) i782;
            if (sha1_32(bArr) == -495631474) {
                break;
            }
        }
        for (int i783 = -39; i783 < -24; i783++) {
            bArr[782] = (byte) i783;
            if (sha1_32(bArr) == 2026754689) {
                break;
            }
        }
        for (int i784 = -45; i784 < -42; i784++) {
            bArr[783] = (byte) i784;
            if (sha1_32(bArr) == -1363048672) {
                break;
            }
        }
        for (int i785 = -111; i785 < -88; i785++) {
            bArr[784] = (byte) i785;
            if (sha1_32(bArr) == -1990526293) {
                break;
            }
        }
        for (int i786 = 48; i786 < 63; i786++) {
            bArr[785] = (byte) i786;
            if (sha1_32(bArr) == 1383152022) {
                break;
            }
        }
        for (int i787 = 36; i787 < 51; i787++) {
            bArr[786] = (byte) i787;
            if (sha1_32(bArr) == -513917078) {
                break;
            }
        }
        for (int i788 = -99; i788 < -93; i788++) {
            bArr[787] = (byte) i788;
            if (sha1_32(bArr) == -360609306) {
                break;
            }
        }
        for (int i789 = -84; i789 < -72; i789++) {
            bArr[788] = (byte) i789;
            if (sha1_32(bArr) == -321011811) {
                break;
            }
        }
        for (int i790 = -9; i790 < 0; i790++) {
            bArr[789] = (byte) i790;
            if (sha1_32(bArr) == -1396851853) {
                break;
            }
        }
        for (int i791 = -100; i791 < -91; i791++) {
            bArr[790] = (byte) i791;
            if (sha1_32(bArr) == -1882700924) {
                break;
            }
        }
        for (int i792 = -99; i792 < -87; i792++) {
            bArr[791] = (byte) i792;
            if (sha1_32(bArr) == -1082762867) {
                break;
            }
        }
        for (int i793 = 101; i793 < 126; i793++) {
            bArr[792] = (byte) i793;
            if (sha1_32(bArr) == 2132131804) {
                break;
            }
        }
        for (int i794 = -86; i794 < -83; i794++) {
            bArr[793] = (byte) i794;
            if (sha1_32(bArr) == -1128269747) {
                break;
            }
        }
        for (int i795 = -67; i795 < -48; i795++) {
            bArr[794] = (byte) i795;
            if (sha1_32(bArr) == 1875001108) {
                break;
            }
        }
        for (int i796 = 117; i796 < 128; i796++) {
            bArr[795] = (byte) i796;
            if (sha1_32(bArr) == -1137131095) {
                break;
            }
        }
        for (int i797 = 104; i797 < 119; i797++) {
            bArr[796] = (byte) i797;
            if (sha1_32(bArr) == 917156108) {
                break;
            }
        }
        for (int i798 = 82; i798 < 99; i798++) {
            bArr[797] = (byte) i798;
            if (sha1_32(bArr) == -1393509043) {
                break;
            }
        }
        for (int i799 = -73; i799 < -69; i799++) {
            bArr[798] = (byte) i799;
            if (sha1_32(bArr) == 162731269) {
                break;
            }
        }
        for (int i800 = -128; i800 < -115; i800++) {
            bArr[799] = (byte) i800;
            if (sha1_32(bArr) == 1827580274) {
                break;
            }
        }
        for (int i801 = 67; i801 < 87; i801++) {
            bArr[800] = (byte) i801;
            if (sha1_32(bArr) == -682496701) {
                break;
            }
        }
        for (int i802 = 94; i802 < 104; i802++) {
            bArr[801] = (byte) i802;
            if (sha1_32(bArr) == -1710412711) {
                break;
            }
        }
        for (int i803 = -94; i803 < -78; i803++) {
            bArr[802] = (byte) i803;
            if (sha1_32(bArr) == 1642618183) {
                break;
            }
        }
        for (int i804 = -69; i804 < -59; i804++) {
            bArr[803] = (byte) i804;
            if (sha1_32(bArr) == -171144801) {
                break;
            }
        }
        for (int i805 = -127; i805 < -108; i805++) {
            bArr[804] = (byte) i805;
            if (sha1_32(bArr) == -204065147) {
                break;
            }
        }
        for (int i806 = -46; i806 < -30; i806++) {
            bArr[805] = (byte) i806;
            if (sha1_32(bArr) == 1160973256) {
                break;
            }
        }
        for (int i807 = -82; i807 < -80; i807++) {
            bArr[806] = (byte) i807;
            if (sha1_32(bArr) == -1096778355) {
                break;
            }
        }
        for (int i808 = -12; i808 < 9; i808++) {
            bArr[807] = (byte) i808;
            if (sha1_32(bArr) == 1578102492) {
                break;
            }
        }
        for (int i809 = 39; i809 < 54; i809++) {
            bArr[808] = (byte) i809;
            if (sha1_32(bArr) == 553413154) {
                break;
            }
        }
        for (int i810 = -67; i810 < -49; i810++) {
            bArr[809] = (byte) i810;
            if (sha1_32(bArr) == 1323701664) {
                break;
            }
        }
        for (int i811 = -110; i811 < -93; i811++) {
            bArr[810] = (byte) i811;
            if (sha1_32(bArr) == 480230604) {
                break;
            }
        }
        for (int i812 = 51; i812 < 63; i812++) {
            bArr[811] = (byte) i812;
            if (sha1_32(bArr) == -1649767233) {
                break;
            }
        }
        for (int i813 = -128; i813 < -120; i813++) {
            bArr[812] = (byte) i813;
            if (sha1_32(bArr) == -1900838443) {
                break;
            }
        }
        for (int i814 = 100; i814 < 109; i814++) {
            bArr[813] = (byte) i814;
            if (sha1_32(bArr) == 640080092) {
                break;
            }
        }
        for (int i815 = 64; i815 < 86; i815++) {
            bArr[814] = (byte) i815;
            if (sha1_32(bArr) == -570832105) {
                break;
            }
        }
        for (int i816 = 6; i816 < 17; i816++) {
            bArr[815] = (byte) i816;
            if (sha1_32(bArr) == -1986481548) {
                break;
            }
        }
        for (int i817 = -9; i817 < 11; i817++) {
            bArr[816] = (byte) i817;
            if (sha1_32(bArr) == 1372264334) {
                break;
            }
        }
        for (int i818 = 87; i818 < 110; i818++) {
            bArr[817] = (byte) i818;
            if (sha1_32(bArr) == -1573878007) {
                break;
            }
        }
        for (int i819 = -28; i819 < -10; i819++) {
            bArr[818] = (byte) i819;
            if (sha1_32(bArr) == 1460777460) {
                break;
            }
        }
        for (int i820 = 67; i820 < 81; i820++) {
            bArr[819] = (byte) i820;
            if (sha1_32(bArr) == -1595144333) {
                break;
            }
        }
        for (int i821 = ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT; i821 < 127; i821++) {
            bArr[820] = (byte) i821;
            if (sha1_32(bArr) == 175126805) {
                break;
            }
        }
        for (int i822 = 109; i822 < 128; i822++) {
            bArr[821] = (byte) i822;
            if (sha1_32(bArr) == 1435592355) {
                break;
            }
        }
        for (int i823 = 88; i823 < 104; i823++) {
            bArr[822] = (byte) i823;
            if (sha1_32(bArr) == -445788097) {
                break;
            }
        }
        for (int i824 = 18; i824 < 38; i824++) {
            bArr[823] = (byte) i824;
            if (sha1_32(bArr) == -926669708) {
                break;
            }
        }
        for (int i825 = -88; i825 < -61; i825++) {
            bArr[824] = (byte) i825;
            if (sha1_32(bArr) == 1541679905) {
                break;
            }
        }
        for (int i826 = -46; i826 < -34; i826++) {
            bArr[825] = (byte) i826;
            if (sha1_32(bArr) == 1490971344) {
                break;
            }
        }
        for (int i827 = -93; i827 < -78; i827++) {
            bArr[826] = (byte) i827;
            if (sha1_32(bArr) == 1527925854) {
                break;
            }
        }
        for (int i828 = 92; i828 < 114; i828++) {
            bArr[827] = (byte) i828;
            if (sha1_32(bArr) == 605376408) {
                break;
            }
        }
        for (int i829 = -48; i829 < -33; i829++) {
            bArr[828] = (byte) i829;
            if (sha1_32(bArr) == 1930240938) {
                break;
            }
        }
        for (int i830 = 59; i830 < 70; i830++) {
            bArr[829] = (byte) i830;
            if (sha1_32(bArr) == -1760330438) {
                break;
            }
        }
        for (int i831 = 97; i831 < 122; i831++) {
            bArr[830] = (byte) i831;
            if (sha1_32(bArr) == 52060465) {
                break;
            }
        }
        for (int i832 = -6; i832 < 8; i832++) {
            bArr[831] = (byte) i832;
            if (sha1_32(bArr) == 52060465) {
                break;
            }
        }
        for (int i833 = 118; i833 < 128; i833++) {
            bArr[832] = (byte) i833;
            if (sha1_32(bArr) == -1356447560) {
                break;
            }
        }
        for (int i834 = 48; i834 < 67; i834++) {
            bArr[833] = (byte) i834;
            if (sha1_32(bArr) == 340073530) {
                break;
            }
        }
        for (int i835 = -119; i835 < -108; i835++) {
            bArr[834] = (byte) i835;
            if (sha1_32(bArr) == -2046245981) {
                break;
            }
        }
        for (int i836 = -124; i836 < -107; i836++) {
            bArr[835] = (byte) i836;
            if (sha1_32(bArr) == -1978827435) {
                break;
            }
        }
        for (int i837 = 21; i837 < 29; i837++) {
            bArr[836] = (byte) i837;
            if (sha1_32(bArr) == -1687709568) {
                break;
            }
        }
        for (int i838 = -36; i838 < -29; i838++) {
            bArr[837] = (byte) i838;
            if (sha1_32(bArr) == 616508779) {
                break;
            }
        }
        for (int i839 = -14; i839 < 0; i839++) {
            bArr[838] = (byte) i839;
            if (sha1_32(bArr) == -2074748141) {
                break;
            }
        }
        for (int i840 = -15; i840 < 3; i840++) {
            bArr[839] = (byte) i840;
            if (sha1_32(bArr) == 168879057) {
                break;
            }
        }
        for (int i841 = -59; i841 < -38; i841++) {
            bArr[840] = (byte) i841;
            if (sha1_32(bArr) == -1106958397) {
                break;
            }
        }
        for (int i842 = 43; i842 < 59; i842++) {
            bArr[841] = (byte) i842;
            if (sha1_32(bArr) == 886084835) {
                break;
            }
        }
        for (int i843 = -117; i843 < -96; i843++) {
            bArr[842] = (byte) i843;
            if (sha1_32(bArr) == 1233590067) {
                break;
            }
        }
        for (int i844 = 26; i844 < 45; i844++) {
            bArr[843] = (byte) i844;
            if (sha1_32(bArr) == 1425079955) {
                break;
            }
        }
        for (int i845 = -62; i845 < -34; i845++) {
            bArr[844] = (byte) i845;
            if (sha1_32(bArr) == 1190320444) {
                break;
            }
        }
        for (int i846 = 15; i846 < 20; i846++) {
            bArr[845] = (byte) i846;
            if (sha1_32(bArr) == -694205021) {
                break;
            }
        }
        for (int i847 = -89; i847 < -63; i847++) {
            bArr[846] = (byte) i847;
            if (sha1_32(bArr) == 1593214310) {
                break;
            }
        }
        for (int i848 = -43; i848 < -30; i848++) {
            bArr[847] = (byte) i848;
            if (sha1_32(bArr) == 1240128586) {
                break;
            }
        }
        for (int i849 = -128; i849 < -110; i849++) {
            bArr[848] = (byte) i849;
            if (sha1_32(bArr) == -1858001761) {
                break;
            }
        }
        for (int i850 = 90; i850 < 112; i850++) {
            bArr[849] = (byte) i850;
            if (sha1_32(bArr) == -1173935824) {
                break;
            }
        }
        for (int i851 = -35; i851 < -10; i851++) {
            bArr[850] = (byte) i851;
            if (sha1_32(bArr) == 1446657686) {
                break;
            }
        }
        for (int i852 = 40; i852 < 56; i852++) {
            bArr[851] = (byte) i852;
            if (sha1_32(bArr) == -1869692344) {
                break;
            }
        }
        for (int i853 = 14; i853 < 27; i853++) {
            bArr[852] = (byte) i853;
            if (sha1_32(bArr) == 101637959) {
                break;
            }
        }
        for (int i854 = -30; i854 < -8; i854++) {
            bArr[853] = (byte) i854;
            if (sha1_32(bArr) == -1528509246) {
                break;
            }
        }
        for (int i855 = 116; i855 < 126; i855++) {
            bArr[854] = (byte) i855;
            if (sha1_32(bArr) == 248835343) {
                break;
            }
        }
        for (int i856 = -34; i856 < -14; i856++) {
            bArr[855] = (byte) i856;
            if (sha1_32(bArr) == -873398299) {
                break;
            }
        }
        for (int i857 = -30; i857 < -8; i857++) {
            bArr[856] = (byte) i857;
            if (sha1_32(bArr) == -1921793021) {
                break;
            }
        }
        for (int i858 = -96; i858 < -77; i858++) {
            bArr[857] = (byte) i858;
            if (sha1_32(bArr) == -834185911) {
                break;
            }
        }
        for (int i859 = 39; i859 < 50; i859++) {
            bArr[858] = (byte) i859;
            if (sha1_32(bArr) == 354441589) {
                break;
            }
        }
        for (int i860 = 27; i860 < 55; i860++) {
            bArr[859] = (byte) i860;
            if (sha1_32(bArr) == -1647167531) {
                break;
            }
        }
        for (int i861 = 57; i861 < 86; i861++) {
            bArr[860] = (byte) i861;
            if (sha1_32(bArr) == -600404256) {
                break;
            }
        }
        for (int i862 = 102; i862 < 107; i862++) {
            bArr[861] = (byte) i862;
            if (sha1_32(bArr) == -2060665049) {
                break;
            }
        }
        for (int i863 = -24; i863 < -8; i863++) {
            bArr[862] = (byte) i863;
            if (sha1_32(bArr) == 495094278) {
                break;
            }
        }
        for (int i864 = 14; i864 < 39; i864++) {
            bArr[863] = (byte) i864;
            if (sha1_32(bArr) == 91231838) {
                break;
            }
        }
        for (int i865 = -119; i865 < -93; i865++) {
            bArr[864] = (byte) i865;
            if (sha1_32(bArr) == 663719650) {
                break;
            }
        }
        for (int i866 = -125; i866 < -110; i866++) {
            bArr[865] = (byte) i866;
            if (sha1_32(bArr) == -731254539) {
                break;
            }
        }
        for (int i867 = 70; i867 < 78; i867++) {
            bArr[866] = (byte) i867;
            if (sha1_32(bArr) == 1769381745) {
                break;
            }
        }
        for (int i868 = -45; i868 < -29; i868++) {
            bArr[867] = (byte) i868;
            if (sha1_32(bArr) == -1231867116) {
                break;
            }
        }
        for (int i869 = -9; i869 < 6; i869++) {
            bArr[868] = (byte) i869;
            if (sha1_32(bArr) == -1075882221) {
                break;
            }
        }
        for (int i870 = 38; i870 < 57; i870++) {
            bArr[869] = (byte) i870;
            if (sha1_32(bArr) == 1568937643) {
                break;
            }
        }
        for (int i871 = -96; i871 < -84; i871++) {
            bArr[870] = (byte) i871;
            if (sha1_32(bArr) == -976215791) {
                break;
            }
        }
        for (int i872 = -35; i872 < -12; i872++) {
            bArr[871] = (byte) i872;
            if (sha1_32(bArr) == 961793191) {
                break;
            }
        }
        for (int i873 = UiPosIndexEnum.HOME_REPLACE_BG_TAB; i873 < 126; i873++) {
            bArr[872] = (byte) i873;
            if (sha1_32(bArr) == -390531020) {
                break;
            }
        }
        for (int i874 = 103; i874 < 124; i874++) {
            bArr[873] = (byte) i874;
            if (sha1_32(bArr) == -335787460) {
                break;
            }
        }
        for (int i875 = UiPosIndexEnum.HOME_COUNTDOWN; i875 < 128; i875++) {
            bArr[874] = (byte) i875;
            if (sha1_32(bArr) == 769213595) {
                break;
            }
        }
        for (int i876 = 98; i876 < 112; i876++) {
            bArr[875] = (byte) i876;
            if (sha1_32(bArr) == 1874956492) {
                break;
            }
        }
        for (int i877 = -100; i877 < -82; i877++) {
            bArr[876] = (byte) i877;
            if (sha1_32(bArr) == -2132165621) {
                break;
            }
        }
        for (int i878 = -8; i878 < -5; i878++) {
            bArr[877] = (byte) i878;
            if (sha1_32(bArr) == 1281088551) {
                break;
            }
        }
        for (int i879 = -6; i879 < 17; i879++) {
            bArr[878] = (byte) i879;
            if (sha1_32(bArr) == -2132762289) {
                break;
            }
        }
        for (int i880 = -6; i880 < 7; i880++) {
            bArr[879] = (byte) i880;
            if (sha1_32(bArr) == -853346310) {
                break;
            }
        }
        for (int i881 = 71; i881 < 78; i881++) {
            bArr[880] = (byte) i881;
            if (sha1_32(bArr) == -1532317087) {
                break;
            }
        }
        for (int i882 = -128; i882 < -105; i882++) {
            bArr[881] = (byte) i882;
            if (sha1_32(bArr) == -1202796078) {
                break;
            }
        }
        for (int i883 = -9; i883 < 3; i883++) {
            bArr[882] = (byte) i883;
            if (sha1_32(bArr) == 1958286644) {
                break;
            }
        }
        for (int i884 = 0; i884 < 28; i884++) {
            bArr[883] = (byte) i884;
            if (sha1_32(bArr) == 1047456372) {
                break;
            }
        }
        for (int i885 = -19; i885 < -4; i885++) {
            bArr[884] = (byte) i885;
            if (sha1_32(bArr) == -996204190) {
                break;
            }
        }
        for (int i886 = -103; i886 < -75; i886++) {
            bArr[885] = (byte) i886;
            if (sha1_32(bArr) == 389813844) {
                break;
            }
        }
        for (int i887 = 63; i887 < 83; i887++) {
            bArr[886] = (byte) i887;
            if (sha1_32(bArr) == -2090916747) {
                break;
            }
        }
        for (int i888 = -112; i888 < -98; i888++) {
            bArr[887] = (byte) i888;
            if (sha1_32(bArr) == -456157415) {
                break;
            }
        }
        for (int i889 = -41; i889 < -31; i889++) {
            bArr[888] = (byte) i889;
            if (sha1_32(bArr) == 1261181424) {
                break;
            }
        }
        for (int i890 = 70; i890 < 83; i890++) {
            bArr[889] = (byte) i890;
            if (sha1_32(bArr) == -2081716582) {
                break;
            }
        }
        for (int i891 = 99; i891 < 117; i891++) {
            bArr[890] = (byte) i891;
            if (sha1_32(bArr) == 2101105241) {
                break;
            }
        }
        for (int i892 = 3; i892 < 12; i892++) {
            bArr[891] = (byte) i892;
            if (sha1_32(bArr) == -615111275) {
                break;
            }
        }
        for (int i893 = -27; i893 < -9; i893++) {
            bArr[892] = (byte) i893;
            if (sha1_32(bArr) == 1721138820) {
                break;
            }
        }
        for (int i894 = -90; i894 < -81; i894++) {
            bArr[893] = (byte) i894;
            if (sha1_32(bArr) == -267568542) {
                break;
            }
        }
        for (int i895 = 61; i895 < 67; i895++) {
            bArr[894] = (byte) i895;
            if (sha1_32(bArr) == 701098461) {
                break;
            }
        }
        for (int i896 = 91; i896 < 99; i896++) {
            bArr[895] = (byte) i896;
            if (sha1_32(bArr) == -215319672) {
                break;
            }
        }
        for (int i897 = -49; i897 < -42; i897++) {
            bArr[896] = (byte) i897;
            if (sha1_32(bArr) == 203774931) {
                break;
            }
        }
        for (int i898 = 15; i898 < 38; i898++) {
            bArr[897] = (byte) i898;
            if (sha1_32(bArr) == -1705012743) {
                break;
            }
        }
        for (int i899 = 19; i899 < 38; i899++) {
            bArr[898] = (byte) i899;
            if (sha1_32(bArr) == -365271552) {
                break;
            }
        }
        for (int i900 = 37; i900 < 62; i900++) {
            bArr[899] = (byte) i900;
            if (sha1_32(bArr) == 1129508429) {
                break;
            }
        }
        for (int i901 = -122; i901 < -109; i901++) {
            bArr[900] = (byte) i901;
            if (sha1_32(bArr) == -559967815) {
                break;
            }
        }
        for (int i902 = 15; i902 < 19; i902++) {
            bArr[901] = (byte) i902;
            if (sha1_32(bArr) == -696772415) {
                break;
            }
        }
        for (int i903 = -8; i903 < 12; i903++) {
            bArr[902] = (byte) i903;
            if (sha1_32(bArr) == 422782161) {
                break;
            }
        }
        for (int i904 = 81; i904 < 89; i904++) {
            bArr[903] = (byte) i904;
            if (sha1_32(bArr) == 1090778681) {
                break;
            }
        }
        for (int i905 = -122; i905 < -99; i905++) {
            bArr[904] = (byte) i905;
            if (sha1_32(bArr) == -1144857626) {
                break;
            }
        }
        for (int i906 = -120; i906 < -92; i906++) {
            bArr[905] = (byte) i906;
            if (sha1_32(bArr) == 1999957487) {
                break;
            }
        }
        for (int i907 = 64; i907 < 83; i907++) {
            bArr[906] = (byte) i907;
            if (sha1_32(bArr) == -54701857) {
                break;
            }
        }
        for (int i908 = -14; i908 < 2; i908++) {
            bArr[907] = (byte) i908;
            if (sha1_32(bArr) == -1915331456) {
                break;
            }
        }
        for (int i909 = -11; i909 < 16; i909++) {
            bArr[908] = (byte) i909;
            if (sha1_32(bArr) == -1915331456) {
                break;
            }
        }
        for (int i910 = -9; i910 < 8; i910++) {
            bArr[909] = (byte) i910;
            if (sha1_32(bArr) == -170064343) {
                break;
            }
        }
        for (int i911 = 89; i911 < 106; i911++) {
            bArr[910] = (byte) i911;
            if (sha1_32(bArr) == -403390338) {
                break;
            }
        }
        for (int i912 = -74; i912 < -58; i912++) {
            bArr[911] = (byte) i912;
            if (sha1_32(bArr) == -230239344) {
                break;
            }
        }
        for (int i913 = -115; i913 < -107; i913++) {
            bArr[912] = (byte) i913;
            if (sha1_32(bArr) == 1217766651) {
                break;
            }
        }
        for (int i914 = -57; i914 < -31; i914++) {
            bArr[913] = (byte) i914;
            if (sha1_32(bArr) == 93840471) {
                break;
            }
        }
        for (int i915 = -123; i915 < -113; i915++) {
            bArr[914] = (byte) i915;
            if (sha1_32(bArr) == -1798799439) {
                break;
            }
        }
        for (int i916 = -128; i916 < -115; i916++) {
            bArr[915] = (byte) i916;
            if (sha1_32(bArr) == 1857539256) {
                break;
            }
        }
        for (int i917 = -128; i917 < -111; i917++) {
            bArr[916] = (byte) i917;
            if (sha1_32(bArr) == 24301083) {
                break;
            }
        }
        for (int i918 = 77; i918 < 94; i918++) {
            bArr[917] = (byte) i918;
            if (sha1_32(bArr) == 2130062458) {
                break;
            }
        }
        for (int i919 = -84; i919 < -57; i919++) {
            bArr[918] = (byte) i919;
            if (sha1_32(bArr) == -1548281356) {
                break;
            }
        }
        for (int i920 = 15; i920 < 43; i920++) {
            bArr[919] = (byte) i920;
            if (sha1_32(bArr) == 979423876) {
                break;
            }
        }
        for (int i921 = -122; i921 < -115; i921++) {
            bArr[920] = (byte) i921;
            if (sha1_32(bArr) == -1766677937) {
                break;
            }
        }
        for (int i922 = 27; i922 < 47; i922++) {
            bArr[921] = (byte) i922;
            if (sha1_32(bArr) == -234477398) {
                break;
            }
        }
        for (int i923 = 19; i923 < 30; i923++) {
            bArr[922] = (byte) i923;
            if (sha1_32(bArr) == 1064804642) {
                break;
            }
        }
        for (int i924 = 13; i924 < 35; i924++) {
            bArr[923] = (byte) i924;
            if (sha1_32(bArr) == -2035542960) {
                break;
            }
        }
        for (int i925 = 32; i925 < 41; i925++) {
            bArr[924] = (byte) i925;
            if (sha1_32(bArr) == 715815345) {
                break;
            }
        }
        for (int i926 = 83; i926 < 99; i926++) {
            bArr[925] = (byte) i926;
            if (sha1_32(bArr) == 714821240) {
                break;
            }
        }
        for (int i927 = 48; i927 < 56; i927++) {
            bArr[926] = (byte) i927;
            if (sha1_32(bArr) == -1866775869) {
                break;
            }
        }
        for (int i928 = -57; i928 < -36; i928++) {
            bArr[927] = (byte) i928;
            if (sha1_32(bArr) == 1211218721) {
                break;
            }
        }
        for (int i929 = 90; i929 < 113; i929++) {
            bArr[928] = (byte) i929;
            if (sha1_32(bArr) == -1917971496) {
                break;
            }
        }
        for (int i930 = 84; i930 < 109; i930++) {
            bArr[929] = (byte) i930;
            if (sha1_32(bArr) == -1540241011) {
                break;
            }
        }
        for (int i931 = -61; i931 < -54; i931++) {
            bArr[930] = (byte) i931;
            if (sha1_32(bArr) == -1726557031) {
                break;
            }
        }
        for (int i932 = 92; i932 < 118; i932++) {
            bArr[931] = (byte) i932;
            if (sha1_32(bArr) == -26227164) {
                break;
            }
        }
        for (int i933 = -89; i933 < -71; i933++) {
            bArr[932] = (byte) i933;
            if (sha1_32(bArr) == 1332102479) {
                break;
            }
        }
        for (int i934 = 23; i934 < 32; i934++) {
            bArr[933] = (byte) i934;
            if (sha1_32(bArr) == -619282506) {
                break;
            }
        }
        for (int i935 = 50; i935 < 69; i935++) {
            bArr[934] = (byte) i935;
            if (sha1_32(bArr) == 1999089551) {
                break;
            }
        }
        for (int i936 = 79; i936 < 109; i936++) {
            bArr[935] = (byte) i936;
            if (sha1_32(bArr) == -1838626215) {
                break;
            }
        }
        for (int i937 = 91; i937 < 98; i937++) {
            bArr[936] = (byte) i937;
            if (sha1_32(bArr) == 296840499) {
                break;
            }
        }
        for (int i938 = -110; i938 < -93; i938++) {
            bArr[937] = (byte) i938;
            if (sha1_32(bArr) == 2005573953) {
                break;
            }
        }
        for (int i939 = 45; i939 < 49; i939++) {
            bArr[938] = (byte) i939;
            if (sha1_32(bArr) == 364652566) {
                break;
            }
        }
        for (int i940 = -78; i940 < -69; i940++) {
            bArr[939] = (byte) i940;
            if (sha1_32(bArr) == -638949151) {
                break;
            }
        }
        for (int i941 = 64; i941 < 85; i941++) {
            bArr[940] = (byte) i941;
            if (sha1_32(bArr) == -341809351) {
                break;
            }
        }
        for (int i942 = -24; i942 < -8; i942++) {
            bArr[941] = (byte) i942;
            if (sha1_32(bArr) == -1596962921) {
                break;
            }
        }
        for (int i943 = -109; i943 < -84; i943++) {
            bArr[942] = (byte) i943;
            if (sha1_32(bArr) == 474783179) {
                break;
            }
        }
        for (int i944 = 38; i944 < 53; i944++) {
            bArr[943] = (byte) i944;
            if (sha1_32(bArr) == 1891179970) {
                break;
            }
        }
        for (int i945 = 43; i945 < 59; i945++) {
            bArr[944] = (byte) i945;
            if (sha1_32(bArr) == 1754160612) {
                break;
            }
        }
        for (int i946 = 56; i946 < 86; i946++) {
            bArr[945] = (byte) i946;
            if (sha1_32(bArr) == 1445807071) {
                break;
            }
        }
        for (int i947 = -22; i947 < -9; i947++) {
            bArr[946] = (byte) i947;
            if (sha1_32(bArr) == 1707569283) {
                break;
            }
        }
        for (int i948 = 92; i948 < 108; i948++) {
            bArr[947] = (byte) i948;
            if (sha1_32(bArr) == -627686734) {
                break;
            }
        }
        for (int i949 = -11; i949 < 6; i949++) {
            bArr[948] = (byte) i949;
            if (sha1_32(bArr) == 1679072110) {
                break;
            }
        }
        for (int i950 = 1; i950 < 17; i950++) {
            bArr[949] = (byte) i950;
            if (sha1_32(bArr) == 313191780) {
                break;
            }
        }
        for (int i951 = -81; i951 < -78; i951++) {
            bArr[950] = (byte) i951;
            if (sha1_32(bArr) == -908888738) {
                break;
            }
        }
        for (int i952 = 14; i952 < 28; i952++) {
            bArr[951] = (byte) i952;
            if (sha1_32(bArr) == -200020917) {
                break;
            }
        }
        for (int i953 = -41; i953 < -30; i953++) {
            bArr[952] = (byte) i953;
            if (sha1_32(bArr) == 1728225933) {
                break;
            }
        }
        for (int i954 = -1; i954 < 12; i954++) {
            bArr[953] = (byte) i954;
            if (sha1_32(bArr) == -1790625237) {
                break;
            }
        }
        for (int i955 = 48; i955 < 68; i955++) {
            bArr[954] = (byte) i955;
            if (sha1_32(bArr) == 1094526144) {
                break;
            }
        }
        for (int i956 = 101; i956 < 121; i956++) {
            bArr[955] = (byte) i956;
            if (sha1_32(bArr) == 13575759) {
                break;
            }
        }
        for (int i957 = 92; i957 < 110; i957++) {
            bArr[956] = (byte) i957;
            if (sha1_32(bArr) == 1790870321) {
                break;
            }
        }
        for (int i958 = 79; i958 < 103; i958++) {
            bArr[957] = (byte) i958;
            if (sha1_32(bArr) == -1711531610) {
                break;
            }
        }
        for (int i959 = -14; i959 < 0; i959++) {
            bArr[958] = (byte) i959;
            if (sha1_32(bArr) == -130032607) {
                break;
            }
        }
        for (int i960 = -128; i960 < -113; i960++) {
            bArr[959] = (byte) i960;
            if (sha1_32(bArr) == 308382370) {
                break;
            }
        }
        for (int i961 = -72; i961 < -49; i961++) {
            bArr[960] = (byte) i961;
            if (sha1_32(bArr) == -817805808) {
                break;
            }
        }
        for (int i962 = -28; i962 < -9; i962++) {
            bArr[961] = (byte) i962;
            if (sha1_32(bArr) == 1913334377) {
                break;
            }
        }
        for (int i963 = 16; i963 < 34; i963++) {
            bArr[962] = (byte) i963;
            if (sha1_32(bArr) == -745875960) {
                break;
            }
        }
        for (int i964 = -90; i964 < -79; i964++) {
            bArr[963] = (byte) i964;
            if (sha1_32(bArr) == 1192730161) {
                break;
            }
        }
        for (int i965 = -58; i965 < -50; i965++) {
            bArr[964] = (byte) i965;
            if (sha1_32(bArr) == 1693912765) {
                break;
            }
        }
        for (int i966 = 64; i966 < 77; i966++) {
            bArr[965] = (byte) i966;
            if (sha1_32(bArr) == 2019646664) {
                break;
            }
        }
        for (int i967 = 109; i967 < 118; i967++) {
            bArr[966] = (byte) i967;
            if (sha1_32(bArr) == 1511653385) {
                break;
            }
        }
        for (int i968 = 95; i968 < 122; i968++) {
            bArr[967] = (byte) i968;
            if (sha1_32(bArr) == 1937476133) {
                break;
            }
        }
        for (int i969 = -7; i969 < 19; i969++) {
            bArr[968] = (byte) i969;
            if (sha1_32(bArr) == 702503133) {
                break;
            }
        }
        for (int i970 = -121; i970 < -108; i970++) {
            bArr[969] = (byte) i970;
            if (sha1_32(bArr) == -747602304) {
                break;
            }
        }
        for (int i971 = 96; i971 < 115; i971++) {
            bArr[970] = (byte) i971;
            if (sha1_32(bArr) == 719367441) {
                break;
            }
        }
        for (int i972 = -128; i972 < -119; i972++) {
            bArr[971] = (byte) i972;
            if (sha1_32(bArr) == -247001557) {
                break;
            }
        }
        for (int i973 = -55; i973 < -28; i973++) {
            bArr[972] = (byte) i973;
            if (sha1_32(bArr) == 1021136291) {
                break;
            }
        }
        for (int i974 = -117; i974 < -104; i974++) {
            bArr[973] = (byte) i974;
            if (sha1_32(bArr) == -727619249) {
                break;
            }
        }
        for (int i975 = 12; i975 < 24; i975++) {
            bArr[974] = (byte) i975;
            if (sha1_32(bArr) == 1556508387) {
                break;
            }
        }
        for (int i976 = -106; i976 < -96; i976++) {
            bArr[975] = (byte) i976;
            if (sha1_32(bArr) == -1747682731) {
                break;
            }
        }
        for (int i977 = -4; i977 < 21; i977++) {
            bArr[976] = (byte) i977;
            if (sha1_32(bArr) == 83426717) {
                break;
            }
        }
        for (int i978 = 0; i978 < 9; i978++) {
            bArr[977] = (byte) i978;
            if (sha1_32(bArr) == 83426717) {
                break;
            }
        }
        for (int i979 = -43; i979 < -13; i979++) {
            bArr[978] = (byte) i979;
            if (sha1_32(bArr) == -752414393) {
                break;
            }
        }
        for (int i980 = -59; i980 < -39; i980++) {
            bArr[979] = (byte) i980;
            if (sha1_32(bArr) == -1908014474) {
                break;
            }
        }
        for (int i981 = 33; i981 < 56; i981++) {
            bArr[980] = (byte) i981;
            if (sha1_32(bArr) == 1836998980) {
                break;
            }
        }
        for (int i982 = -14; i982 < 2; i982++) {
            bArr[981] = (byte) i982;
            if (sha1_32(bArr) == -1760069536) {
                break;
            }
        }
        for (int i983 = -71; i983 < -48; i983++) {
            bArr[982] = (byte) i983;
            if (sha1_32(bArr) == -511733328) {
                break;
            }
        }
        for (int i984 = -126; i984 < -123; i984++) {
            bArr[983] = (byte) i984;
            if (sha1_32(bArr) == 1258471690) {
                break;
            }
        }
        for (int i985 = 54; i985 < 66; i985++) {
            bArr[984] = (byte) i985;
            if (sha1_32(bArr) == -755523166) {
                break;
            }
        }
        for (int i986 = 72; i986 < 83; i986++) {
            bArr[985] = (byte) i986;
            if (sha1_32(bArr) == -1510746245) {
                break;
            }
        }
        for (int i987 = -114; i987 < -98; i987++) {
            bArr[986] = (byte) i987;
            if (sha1_32(bArr) == -1845039310) {
                break;
            }
        }
        for (int i988 = 63; i988 < 85; i988++) {
            bArr[987] = (byte) i988;
            if (sha1_32(bArr) == -1614459423) {
                break;
            }
        }
        for (int i989 = -107; i989 < -100; i989++) {
            bArr[988] = (byte) i989;
            if (sha1_32(bArr) == 1281371361) {
                break;
            }
        }
        for (int i990 = -127; i990 < -102; i990++) {
            bArr[989] = (byte) i990;
            if (sha1_32(bArr) == -747642585) {
                break;
            }
        }
        for (int i991 = -83; i991 < -74; i991++) {
            bArr[990] = (byte) i991;
            if (sha1_32(bArr) == -897602037) {
                break;
            }
        }
        for (int i992 = -35; i992 < -14; i992++) {
            bArr[991] = (byte) i992;
            if (sha1_32(bArr) == -1879040130) {
                break;
            }
        }
        for (int i993 = 18; i993 < 33; i993++) {
            bArr[992] = (byte) i993;
            if (sha1_32(bArr) == 302881641) {
                break;
            }
        }
        for (int i994 = -8; i994 < 14; i994++) {
            bArr[993] = (byte) i994;
            if (sha1_32(bArr) == -1104388909) {
                break;
            }
        }
        for (int i995 = -39; i995 < -34; i995++) {
            bArr[994] = (byte) i995;
            if (sha1_32(bArr) == 1553263089) {
                break;
            }
        }
        for (int i996 = -126; i996 < -105; i996++) {
            bArr[995] = (byte) i996;
            if (sha1_32(bArr) == -96373607) {
                break;
            }
        }
        for (int i997 = -108; i997 < -92; i997++) {
            bArr[996] = (byte) i997;
            if (sha1_32(bArr) == 372230013) {
                break;
            }
        }
        for (int i998 = 70; i998 < 82; i998++) {
            bArr[997] = (byte) i998;
            if (sha1_32(bArr) == 2143801199) {
                break;
            }
        }
        for (int i999 = -109; i999 < -100; i999++) {
            bArr[998] = (byte) i999;
            if (sha1_32(bArr) == -35491073) {
                break;
            }
        }
        for (int i1000 = 29; i1000 < 32; i1000++) {
            bArr[999] = (byte) i1000;
            if (sha1_32(bArr) == -2003672541) {
                break;
            }
        }
        for (int i1001 = 13; i1001 < 29; i1001++) {
            bArr[1000] = (byte) i1001;
            if (sha1_32(bArr) == 1109095624) {
                break;
            }
        }
        for (int i1002 = 91; i1002 < 93; i1002++) {
            bArr[1001] = (byte) i1002;
            if (sha1_32(bArr) == 1302977916) {
                break;
            }
        }
        for (int i1003 = -39; i1003 < -27; i1003++) {
            bArr[1002] = (byte) i1003;
            if (sha1_32(bArr) == 1268252168) {
                break;
            }
        }
        for (int i1004 = 102; i1004 < 119; i1004++) {
            bArr[1003] = (byte) i1004;
            if (sha1_32(bArr) == -671908319) {
                break;
            }
        }
        for (int i1005 = -50; i1005 < -32; i1005++) {
            bArr[1004] = (byte) i1005;
            if (sha1_32(bArr) == -1282663454) {
                break;
            }
        }
        for (int i1006 = 69; i1006 < 95; i1006++) {
            bArr[1005] = (byte) i1006;
            if (sha1_32(bArr) == 1124145395) {
                break;
            }
        }
        for (int i1007 = 119; i1007 < 128; i1007++) {
            bArr[1006] = (byte) i1007;
            if (sha1_32(bArr) == 828616483) {
                break;
            }
        }
        for (int i1008 = -103; i1008 < -87; i1008++) {
            bArr[1007] = (byte) i1008;
            if (sha1_32(bArr) == -1943322156) {
                break;
            }
        }
        for (int i1009 = UiPosIndexEnum.HOME_REPLACE_BG_TAB; i1009 < 125; i1009++) {
            bArr[1008] = (byte) i1009;
            if (sha1_32(bArr) == -2127223489) {
                break;
            }
        }
        for (int i1010 = -32; i1010 < -24; i1010++) {
            bArr[1009] = (byte) i1010;
            if (sha1_32(bArr) == 859156696) {
                break;
            }
        }
        for (int i1011 = -70; i1011 < -49; i1011++) {
            bArr[1010] = (byte) i1011;
            if (sha1_32(bArr) == -1357925023) {
                break;
            }
        }
        for (int i1012 = -128; i1012 < -116; i1012++) {
            bArr[1011] = (byte) i1012;
            if (sha1_32(bArr) == 2030497439) {
                break;
            }
        }
        for (int i1013 = -85; i1013 < -66; i1013++) {
            bArr[1012] = (byte) i1013;
            if (sha1_32(bArr) == 1385932103) {
                break;
            }
        }
        for (int i1014 = -72; i1014 < -53; i1014++) {
            bArr[1013] = (byte) i1014;
            if (sha1_32(bArr) == -50283473) {
                break;
            }
        }
        for (int i1015 = 16; i1015 < 29; i1015++) {
            bArr[1014] = (byte) i1015;
            if (sha1_32(bArr) == 238862386) {
                break;
            }
        }
        for (int i1016 = 30; i1016 < 54; i1016++) {
            bArr[1015] = (byte) i1016;
            if (sha1_32(bArr) == -1413526002) {
                break;
            }
        }
        for (int i1017 = -14; i1017 < 9; i1017++) {
            bArr[1016] = (byte) i1017;
            if (sha1_32(bArr) == -1620743467) {
                break;
            }
        }
        for (int i1018 = -19; i1018 < -7; i1018++) {
            bArr[1017] = (byte) i1018;
            if (sha1_32(bArr) == 159412122) {
                break;
            }
        }
        for (int i1019 = 69; i1019 < 84; i1019++) {
            bArr[1018] = (byte) i1019;
            if (sha1_32(bArr) == -1835936066) {
                break;
            }
        }
        for (int i1020 = -75; i1020 < -61; i1020++) {
            bArr[1019] = (byte) i1020;
            if (sha1_32(bArr) == -188965791) {
                break;
            }
        }
        for (int i1021 = 13; i1021 < 24; i1021++) {
            bArr[1020] = (byte) i1021;
            if (sha1_32(bArr) == -1120069587) {
                break;
            }
        }
        for (int i1022 = -97; i1022 < -94; i1022++) {
            bArr[1021] = (byte) i1022;
            if (sha1_32(bArr) == -933513865) {
                break;
            }
        }
        for (int i1023 = -108; i1023 < -96; i1023++) {
            bArr[1022] = (byte) i1023;
            if (sha1_32(bArr) == 1543596074) {
                break;
            }
        }
        for (int i1024 = -48; i1024 < -32; i1024++) {
            bArr[1023] = (byte) i1024;
            if (sha1_32(bArr) == 443938121) {
                break;
            }
        }
        for (int i1025 = UiPosIndexEnum.HOME_REPLACE_BG_TAB; i1025 < 128; i1025++) {
            bArr[1024] = (byte) i1025;
            if (sha1_32(bArr) == -960120419) {
                break;
            }
        }
        for (int i1026 = -46; i1026 < -17; i1026++) {
            bArr[1025] = (byte) i1026;
            if (sha1_32(bArr) == -1218007317) {
                break;
            }
        }
        for (int i1027 = 84; i1027 < 99; i1027++) {
            bArr[1026] = (byte) i1027;
            if (sha1_32(bArr) == -1156565013) {
                break;
            }
        }
        for (int i1028 = 101; i1028 < 121; i1028++) {
            bArr[1027] = (byte) i1028;
            if (sha1_32(bArr) == 920183336) {
                break;
            }
        }
        for (int i1029 = 24; i1029 < 46; i1029++) {
            bArr[1028] = (byte) i1029;
            if (sha1_32(bArr) == 814874380) {
                break;
            }
        }
        for (int i1030 = -112; i1030 < -106; i1030++) {
            bArr[1029] = (byte) i1030;
            if (sha1_32(bArr) == 720983986) {
                break;
            }
        }
        for (int i1031 = -26; i1031 < -10; i1031++) {
            bArr[1030] = (byte) i1031;
            if (sha1_32(bArr) == -962227388) {
                break;
            }
        }
        for (int i1032 = -67; i1032 < -54; i1032++) {
            bArr[1031] = (byte) i1032;
            if (sha1_32(bArr) == -2091948991) {
                break;
            }
        }
        for (int i1033 = -128; i1033 < -109; i1033++) {
            bArr[1032] = (byte) i1033;
            if (sha1_32(bArr) == -62296483) {
                break;
            }
        }
        for (int i1034 = 28; i1034 < 37; i1034++) {
            bArr[1033] = (byte) i1034;
            if (sha1_32(bArr) == -109040310) {
                break;
            }
        }
        for (int i1035 = 13; i1035 < 26; i1035++) {
            bArr[1034] = (byte) i1035;
            if (sha1_32(bArr) == -1272545675) {
                break;
            }
        }
        for (int i1036 = -102; i1036 < -76; i1036++) {
            bArr[1035] = (byte) i1036;
            if (sha1_32(bArr) == -1894900444) {
                break;
            }
        }
        for (int i1037 = -20; i1037 < -4; i1037++) {
            bArr[1036] = (byte) i1037;
            if (sha1_32(bArr) == -1737555379) {
                break;
            }
        }
        for (int i1038 = 109; i1038 < 116; i1038++) {
            bArr[1037] = (byte) i1038;
            if (sha1_32(bArr) == 1143929466) {
                break;
            }
        }
        for (int i1039 = 38; i1039 < 57; i1039++) {
            bArr[1038] = (byte) i1039;
            if (sha1_32(bArr) == -1218226767) {
                break;
            }
        }
        for (int i1040 = -128; i1040 < -119; i1040++) {
            bArr[1039] = (byte) i1040;
            if (sha1_32(bArr) == 199958266) {
                break;
            }
        }
        for (int i1041 = -50; i1041 < -38; i1041++) {
            bArr[1040] = (byte) i1041;
            if (sha1_32(bArr) == -1663055935) {
                break;
            }
        }
        for (int i1042 = 49; i1042 < 64; i1042++) {
            bArr[1041] = (byte) i1042;
            if (sha1_32(bArr) == -1473645600) {
                break;
            }
        }
        for (int i1043 = 89; i1043 < 113; i1043++) {
            bArr[1042] = (byte) i1043;
            if (sha1_32(bArr) == 923315301) {
                break;
            }
        }
        for (int i1044 = 13; i1044 < 37; i1044++) {
            bArr[1043] = (byte) i1044;
            if (sha1_32(bArr) == -2034558684) {
                break;
            }
        }
        for (int i1045 = -23; i1045 < -14; i1045++) {
            bArr[1044] = (byte) i1045;
            if (sha1_32(bArr) == -510369053) {
                break;
            }
        }
        for (int i1046 = 70; i1046 < 81; i1046++) {
            bArr[1045] = (byte) i1046;
            if (sha1_32(bArr) == 1662780249) {
                break;
            }
        }
        for (int i1047 = -70; i1047 < -44; i1047++) {
            bArr[1046] = (byte) i1047;
            if (sha1_32(bArr) == 823542941) {
                break;
            }
        }
        for (int i1048 = 47; i1048 < 65; i1048++) {
            bArr[1047] = (byte) i1048;
            if (sha1_32(bArr) == -457957998) {
                break;
            }
        }
        for (int i1049 = -56; i1049 < -35; i1049++) {
            bArr[1048] = (byte) i1049;
            if (sha1_32(bArr) == 1631521661) {
                break;
            }
        }
        for (int i1050 = -91; i1050 < -64; i1050++) {
            bArr[1049] = (byte) i1050;
            if (sha1_32(bArr) == -532760259) {
                break;
            }
        }
        for (int i1051 = 97; i1051 < 114; i1051++) {
            bArr[1050] = (byte) i1051;
            if (sha1_32(bArr) == -304917137) {
                break;
            }
        }
        for (int i1052 = -7; i1052 < 12; i1052++) {
            bArr[1051] = (byte) i1052;
            if (sha1_32(bArr) == 857884466) {
                break;
            }
        }
        for (int i1053 = 89; i1053 < 97; i1053++) {
            bArr[1052] = (byte) i1053;
            if (sha1_32(bArr) == -1600480835) {
                break;
            }
        }
        for (int i1054 = 12; i1054 < 22; i1054++) {
            bArr[1053] = (byte) i1054;
            if (sha1_32(bArr) == 1675248057) {
                break;
            }
        }
        for (int i1055 = 49; i1055 < 62; i1055++) {
            bArr[1054] = (byte) i1055;
            if (sha1_32(bArr) == 1946584114) {
                break;
            }
        }
        for (int i1056 = 71; i1056 < 94; i1056++) {
            bArr[1055] = (byte) i1056;
            if (sha1_32(bArr) == 442447563) {
                break;
            }
        }
        for (int i1057 = -6; i1057 < 19; i1057++) {
            bArr[1056] = (byte) i1057;
            if (sha1_32(bArr) == 971438704) {
                break;
            }
        }
        for (int i1058 = -103; i1058 < -86; i1058++) {
            bArr[1057] = (byte) i1058;
            if (sha1_32(bArr) == 1256553065) {
                break;
            }
        }
        for (int i1059 = 97; i1059 < 114; i1059++) {
            bArr[1058] = (byte) i1059;
            if (sha1_32(bArr) == 12981899) {
                break;
            }
        }
        for (int i1060 = 62; i1060 < 83; i1060++) {
            bArr[1059] = (byte) i1060;
            if (sha1_32(bArr) == 1771331153) {
                break;
            }
        }
        for (int i1061 = 22; i1061 < 31; i1061++) {
            bArr[1060] = (byte) i1061;
            if (sha1_32(bArr) == 1419000210) {
                break;
            }
        }
        for (int i1062 = -118; i1062 < -99; i1062++) {
            bArr[1061] = (byte) i1062;
            if (sha1_32(bArr) == 1068476262) {
                break;
            }
        }
        for (int i1063 = -12; i1063 < 11; i1063++) {
            bArr[1062] = (byte) i1063;
            if (sha1_32(bArr) == -1559105763) {
                break;
            }
        }
        for (int i1064 = -91; i1064 < -75; i1064++) {
            bArr[1063] = (byte) i1064;
            if (sha1_32(bArr) == 1517248957) {
                break;
            }
        }
        for (int i1065 = 44; i1065 < 65; i1065++) {
            bArr[1064] = (byte) i1065;
            if (sha1_32(bArr) == -1668548251) {
                break;
            }
        }
        for (int i1066 = 5; i1066 < 17; i1066++) {
            bArr[1065] = (byte) i1066;
            if (sha1_32(bArr) == 1975772377) {
                break;
            }
        }
        for (int i1067 = -77; i1067 < -71; i1067++) {
            bArr[1066] = (byte) i1067;
            if (sha1_32(bArr) == -1084901608) {
                break;
            }
        }
        for (int i1068 = -7; i1068 < 5; i1068++) {
            bArr[1067] = (byte) i1068;
            if (sha1_32(bArr) == -315279702) {
                break;
            }
        }
        for (int i1069 = -122; i1069 < -112; i1069++) {
            bArr[1068] = (byte) i1069;
            if (sha1_32(bArr) == 607881537) {
                break;
            }
        }
        for (int i1070 = 5; i1070 < 26; i1070++) {
            bArr[1069] = (byte) i1070;
            if (sha1_32(bArr) == 214796395) {
                break;
            }
        }
        for (int i1071 = 102; i1071 < 110; i1071++) {
            bArr[1070] = (byte) i1071;
            if (sha1_32(bArr) == -1835792130) {
                break;
            }
        }
        for (int i1072 = 92; i1072 < 105; i1072++) {
            bArr[1071] = (byte) i1072;
            if (sha1_32(bArr) == -609121819) {
                break;
            }
        }
        for (int i1073 = -19; i1073 < -4; i1073++) {
            bArr[1072] = (byte) i1073;
            if (sha1_32(bArr) == 680486231) {
                break;
            }
        }
        for (int i1074 = 71; i1074 < 84; i1074++) {
            bArr[1073] = (byte) i1074;
            if (sha1_32(bArr) == 674057801) {
                break;
            }
        }
        for (int i1075 = -77; i1075 < -61; i1075++) {
            bArr[1074] = (byte) i1075;
            if (sha1_32(bArr) == 819218042) {
                break;
            }
        }
        for (int i1076 = 103; i1076 < 113; i1076++) {
            bArr[1075] = (byte) i1076;
            if (sha1_32(bArr) == 794037008) {
                break;
            }
        }
        for (int i1077 = -44; i1077 < -23; i1077++) {
            bArr[1076] = (byte) i1077;
            if (sha1_32(bArr) == -263011639) {
                break;
            }
        }
        for (int i1078 = -25; i1078 < -18; i1078++) {
            bArr[1077] = (byte) i1078;
            if (sha1_32(bArr) == -342754163) {
                break;
            }
        }
        for (int i1079 = 50; i1079 < 59; i1079++) {
            bArr[1078] = (byte) i1079;
            if (sha1_32(bArr) == -1789847112) {
                break;
            }
        }
        for (int i1080 = 77; i1080 < 95; i1080++) {
            bArr[1079] = (byte) i1080;
            if (sha1_32(bArr) == 1780472089) {
                break;
            }
        }
        for (int i1081 = -119; i1081 < -98; i1081++) {
            bArr[1080] = (byte) i1081;
            if (sha1_32(bArr) == 1768045048) {
                break;
            }
        }
        for (int i1082 = -103; i1082 < -73; i1082++) {
            bArr[1081] = (byte) i1082;
            if (sha1_32(bArr) == 592164297) {
                break;
            }
        }
        for (int i1083 = -128; i1083 < -120; i1083++) {
            bArr[1082] = (byte) i1083;
            if (sha1_32(bArr) == -1686175245) {
                break;
            }
        }
        for (int i1084 = 31; i1084 < 46; i1084++) {
            bArr[1083] = (byte) i1084;
            if (sha1_32(bArr) == 693989118) {
                break;
            }
        }
        for (int i1085 = 0; i1085 < 15; i1085++) {
            bArr[1084] = (byte) i1085;
            if (sha1_32(bArr) == 751366989) {
                break;
            }
        }
        for (int i1086 = -27; i1086 < 1; i1086++) {
            bArr[1085] = (byte) i1086;
            if (sha1_32(bArr) == 966182900) {
                break;
            }
        }
        for (int i1087 = -7; i1087 < 13; i1087++) {
            bArr[1086] = (byte) i1087;
            if (sha1_32(bArr) == 205404760) {
                break;
            }
        }
        for (int i1088 = -86; i1088 < -69; i1088++) {
            bArr[1087] = (byte) i1088;
            if (sha1_32(bArr) == -1319977119) {
                break;
            }
        }
        for (int i1089 = 72; i1089 < 89; i1089++) {
            bArr[1088] = (byte) i1089;
            if (sha1_32(bArr) == 526489565) {
                break;
            }
        }
        for (int i1090 = 115; i1090 < 128; i1090++) {
            bArr[1089] = (byte) i1090;
            if (sha1_32(bArr) == -1840837004) {
                break;
            }
        }
        for (int i1091 = 68; i1091 < 84; i1091++) {
            bArr[1090] = (byte) i1091;
            if (sha1_32(bArr) == -1989412492) {
                break;
            }
        }
        for (int i1092 = 93; i1092 < 107; i1092++) {
            bArr[1091] = (byte) i1092;
            if (sha1_32(bArr) == 2048076101) {
                break;
            }
        }
        for (int i1093 = -124; i1093 < -107; i1093++) {
            bArr[1092] = (byte) i1093;
            if (sha1_32(bArr) == 164259833) {
                break;
            }
        }
        for (int i1094 = -121; i1094 < -96; i1094++) {
            bArr[1093] = (byte) i1094;
            if (sha1_32(bArr) == -2142708320) {
                break;
            }
        }
        for (int i1095 = -75; i1095 < -67; i1095++) {
            bArr[1094] = (byte) i1095;
            if (sha1_32(bArr) == 454307144) {
                break;
            }
        }
        for (int i1096 = -41; i1096 < -25; i1096++) {
            bArr[1095] = (byte) i1096;
            if (sha1_32(bArr) == -715866327) {
                break;
            }
        }
        bArr[1096] = b;
        sha1_32(bArr);
        for (int i1097 = 29; i1097 < 45; i1097++) {
            bArr[1097] = (byte) i1097;
            if (sha1_32(bArr) == -90200876) {
                break;
            }
        }
        for (int i1098 = 80; i1098 < 84; i1098++) {
            bArr[1098] = (byte) i1098;
            if (sha1_32(bArr) == -1731431446) {
                break;
            }
        }
        for (int i1099 = 52; i1099 < 66; i1099++) {
            bArr[1099] = (byte) i1099;
            if (sha1_32(bArr) == -364659627) {
                break;
            }
        }
        for (int i1100 = 73; i1100 < 80; i1100++) {
            bArr[1100] = (byte) i1100;
            if (sha1_32(bArr) == -187621910) {
                break;
            }
        }
        for (int i1101 = 102; i1101 < 116; i1101++) {
            bArr[1101] = (byte) i1101;
            if (sha1_32(bArr) == -932019565) {
                break;
            }
        }
        for (int i1102 = -70; i1102 < -54; i1102++) {
            bArr[1102] = (byte) i1102;
            if (sha1_32(bArr) == 61821508) {
                break;
            }
        }
        for (int i1103 = -128; i1103 < -114; i1103++) {
            bArr[1103] = (byte) i1103;
            if (sha1_32(bArr) == 884511385) {
                break;
            }
        }
        for (int i1104 = 68; i1104 < 81; i1104++) {
            bArr[1104] = (byte) i1104;
            if (sha1_32(bArr) == -1233560407) {
                break;
            }
        }
        for (int i1105 = 52; i1105 < 55; i1105++) {
            bArr[1105] = (byte) i1105;
            if (sha1_32(bArr) == -1428015871) {
                break;
            }
        }
        for (int i1106 = -106; i1106 < -91; i1106++) {
            bArr[1106] = (byte) i1106;
            if (sha1_32(bArr) == 98007847) {
                break;
            }
        }
        for (int i1107 = 109; i1107 < 127; i1107++) {
            bArr[1107] = (byte) i1107;
            if (sha1_32(bArr) == -258451500) {
                break;
            }
        }
        for (int i1108 = -16; i1108 < 3; i1108++) {
            bArr[1108] = (byte) i1108;
            if (sha1_32(bArr) == -207941824) {
                break;
            }
        }
        for (int i1109 = 29; i1109 < 55; i1109++) {
            bArr[1109] = (byte) i1109;
            if (sha1_32(bArr) == -435684337) {
                break;
            }
        }
        for (int i1110 = -128; i1110 < -115; i1110++) {
            bArr[1110] = (byte) i1110;
            if (sha1_32(bArr) == -1496373520) {
                break;
            }
        }
        for (int i1111 = -128; i1111 < -112; i1111++) {
            bArr[1111] = (byte) i1111;
            if (sha1_32(bArr) == -536611901) {
                break;
            }
        }
        for (int i1112 = 47; i1112 < 63; i1112++) {
            bArr[1112] = (byte) i1112;
            if (sha1_32(bArr) == -892072993) {
                break;
            }
        }
        for (int i1113 = -125; i1113 < -115; i1113++) {
            bArr[1113] = (byte) i1113;
            if (sha1_32(bArr) == 1312457534) {
                break;
            }
        }
        for (int i1114 = -40; i1114 < -17; i1114++) {
            bArr[1114] = (byte) i1114;
            if (sha1_32(bArr) == -1442448230) {
                break;
            }
        }
        for (int i1115 = -15; i1115 < -2; i1115++) {
            bArr[1115] = (byte) i1115;
            if (sha1_32(bArr) == -596429884) {
                break;
            }
        }
        for (int i1116 = -125; i1116 < -97; i1116++) {
            bArr[1116] = (byte) i1116;
            if (sha1_32(bArr) == -1278073922) {
                break;
            }
        }
        for (int i1117 = -73; i1117 < -59; i1117++) {
            bArr[1117] = (byte) i1117;
            if (sha1_32(bArr) == -861567909) {
                break;
            }
        }
        for (int i1118 = -127; i1118 < -115; i1118++) {
            bArr[1118] = (byte) i1118;
            if (sha1_32(bArr) == -1142968705) {
                break;
            }
        }
        for (int i1119 = -17; i1119 < -3; i1119++) {
            bArr[1119] = (byte) i1119;
            if (sha1_32(bArr) == 1094097157) {
                break;
            }
        }
        for (int i1120 = -71; i1120 < -61; i1120++) {
            bArr[1120] = (byte) i1120;
            if (sha1_32(bArr) == 1772704108) {
                break;
            }
        }
        for (int i1121 = 11; i1121 < 22; i1121++) {
            bArr[1121] = (byte) i1121;
            if (sha1_32(bArr) == 1776689129) {
                break;
            }
        }
        for (int i1122 = -20; i1122 < -13; i1122++) {
            bArr[1122] = (byte) i1122;
            if (sha1_32(bArr) == -1516521891) {
                break;
            }
        }
        for (int i1123 = -66; i1123 < -43; i1123++) {
            bArr[1123] = (byte) i1123;
            if (sha1_32(bArr) == 2113637354) {
                break;
            }
        }
        for (int i1124 = -44; i1124 < -21; i1124++) {
            bArr[1124] = (byte) i1124;
            if (sha1_32(bArr) == -961248670) {
                break;
            }
        }
        for (int i1125 = -10; i1125 < 6; i1125++) {
            bArr[1125] = (byte) i1125;
            if (sha1_32(bArr) == 593731927) {
                break;
            }
        }
        for (int i1126 = -101; i1126 < -77; i1126++) {
            bArr[1126] = (byte) i1126;
            if (sha1_32(bArr) == 1275479351) {
                break;
            }
        }
        for (int i1127 = -71; i1127 < -51; i1127++) {
            bArr[1127] = (byte) i1127;
            if (sha1_32(bArr) == -709970623) {
                break;
            }
        }
        for (int i1128 = -128; i1128 < -117; i1128++) {
            bArr[1128] = (byte) i1128;
            if (sha1_32(bArr) == -1109282352) {
                break;
            }
        }
        for (int i1129 = -103; i1129 < -85; i1129++) {
            bArr[1129] = (byte) i1129;
            if (sha1_32(bArr) == -838766289) {
                break;
            }
        }
        for (int i1130 = 109; i1130 < 128; i1130++) {
            bArr[1130] = (byte) i1130;
            if (sha1_32(bArr) == 2033926685) {
                break;
            }
        }
        bArr[1131] = (byte) 62;
        sha1_32(bArr);
        for (int i1131 = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE; i1131 < 128; i1131++) {
            bArr[1132] = (byte) i1131;
            if (sha1_32(bArr) == 893643323) {
                break;
            }
        }
        for (int i1132 = -120; i1132 < -100; i1132++) {
            bArr[1133] = (byte) i1132;
            if (sha1_32(bArr) == 112705113) {
                break;
            }
        }
        for (int i1133 = 97; i1133 < 110; i1133++) {
            bArr[1134] = (byte) i1133;
            if (sha1_32(bArr) == -1556263588) {
                break;
            }
        }
        for (int i1134 = 29; i1134 < 35; i1134++) {
            bArr[1135] = (byte) i1134;
            if (sha1_32(bArr) == -699573807) {
                break;
            }
        }
        for (int i1135 = ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT; i1135 < 128; i1135++) {
            bArr[1136] = (byte) i1135;
            if (sha1_32(bArr) == 134600403) {
                break;
            }
        }
        for (int i1136 = UiPosIndexEnum.HOME_COUNTDOWN; i1136 < 124; i1136++) {
            bArr[1137] = (byte) i1136;
            if (sha1_32(bArr) == 232842866) {
                break;
            }
        }
        for (int i1137 = 84; i1137 < 89; i1137++) {
            bArr[1138] = (byte) i1137;
            if (sha1_32(bArr) == 1211075336) {
                break;
            }
        }
        for (int i1138 = -12; i1138 < 13; i1138++) {
            bArr[1139] = (byte) i1138;
            if (sha1_32(bArr) == 1211075336) {
                break;
            }
        }
        for (int i1139 = -53; i1139 < -39; i1139++) {
            bArr[1140] = (byte) i1139;
            if (sha1_32(bArr) == 1295462706) {
                break;
            }
        }
        for (int i1140 = 11; i1140 < 28; i1140++) {
            bArr[1141] = (byte) i1140;
            if (sha1_32(bArr) == -1364034027) {
                break;
            }
        }
        for (int i1141 = 32; i1141 < 46; i1141++) {
            bArr[1142] = (byte) i1141;
            if (sha1_32(bArr) == 982904041) {
                break;
            }
        }
        for (int i1142 = -120; i1142 < -96; i1142++) {
            bArr[1143] = (byte) i1142;
            if (sha1_32(bArr) == -586006067) {
                break;
            }
        }
        for (int i1143 = 60; i1143 < 84; i1143++) {
            bArr[1144] = (byte) i1143;
            if (sha1_32(bArr) == -1745564389) {
                break;
            }
        }
        for (int i1144 = -80; i1144 < -75; i1144++) {
            bArr[1145] = (byte) i1144;
            if (sha1_32(bArr) == 350234491) {
                break;
            }
        }
        for (int i1145 = 78; i1145 < 95; i1145++) {
            bArr[1146] = (byte) i1145;
            if (sha1_32(bArr) == 1656400268) {
                break;
            }
        }
        for (int i1146 = 20; i1146 < 30; i1146++) {
            bArr[1147] = (byte) i1146;
            if (sha1_32(bArr) == 1818654874) {
                break;
            }
        }
        for (int i1147 = 68; i1147 < 76; i1147++) {
            bArr[1148] = (byte) i1147;
            if (sha1_32(bArr) == 1960784283) {
                break;
            }
        }
        for (int i1148 = 37; i1148 < 56; i1148++) {
            bArr[1149] = (byte) i1148;
            if (sha1_32(bArr) == -2066641967) {
                break;
            }
        }
        for (int i1149 = -33; i1149 < -22; i1149++) {
            bArr[1150] = (byte) i1149;
            if (sha1_32(bArr) == 795735631) {
                break;
            }
        }
        for (int i1150 = -108; i1150 < -95; i1150++) {
            bArr[1151] = (byte) i1150;
            if (sha1_32(bArr) == -1426984720) {
                break;
            }
        }
        bArr[1152] = b;
        sha1_32(bArr);
        for (int i1151 = 69; i1151 < 97; i1151++) {
            bArr[1153] = (byte) i1151;
            if (sha1_32(bArr) == -567974643) {
                break;
            }
        }
        for (int i1152 = -1; i1152 < 12; i1152++) {
            bArr[1154] = (byte) i1152;
            if (sha1_32(bArr) == -1863869542) {
                break;
            }
        }
        for (int i1153 = 108; i1153 < 120; i1153++) {
            bArr[1155] = (byte) i1153;
            if (sha1_32(bArr) == 769915464) {
                break;
            }
        }
        for (int i1154 = 79; i1154 < 84; i1154++) {
            bArr[1156] = (byte) i1154;
            if (sha1_32(bArr) == 1416920428) {
                break;
            }
        }
        for (int i1155 = 59; i1155 < 80; i1155++) {
            bArr[1157] = (byte) i1155;
            if (sha1_32(bArr) == -1281341480) {
                break;
            }
        }
        for (int i1156 = 111; i1156 < 128; i1156++) {
            bArr[1158] = (byte) i1156;
            if (sha1_32(bArr) == -391554658) {
                break;
            }
        }
        for (int i1157 = -112; i1157 < -96; i1157++) {
            bArr[1159] = (byte) i1157;
            if (sha1_32(bArr) == -187265270) {
                break;
            }
        }
        for (int i1158 = 30; i1158 < 46; i1158++) {
            bArr[1160] = (byte) i1158;
            if (sha1_32(bArr) == 1619272186) {
                break;
            }
        }
        for (int i1159 = -128; i1159 < -111; i1159++) {
            bArr[1161] = (byte) i1159;
            if (sha1_32(bArr) == 2093355926) {
                break;
            }
        }
        for (int i1160 = 100; i1160 < 122; i1160++) {
            bArr[1162] = (byte) i1160;
            if (sha1_32(bArr) == 38762491) {
                break;
            }
        }
        for (int i1161 = -92; i1161 < -74; i1161++) {
            bArr[1163] = (byte) i1161;
            if (sha1_32(bArr) == 718080313) {
                break;
            }
        }
        for (int i1162 = -128; i1162 < -110; i1162++) {
            bArr[1164] = (byte) i1162;
            if (sha1_32(bArr) == 1342845096) {
                break;
            }
        }
        for (int i1163 = -42; i1163 < -36; i1163++) {
            bArr[1165] = (byte) i1163;
            if (sha1_32(bArr) == 1977653818) {
                break;
            }
        }
        for (int i1164 = 66; i1164 < 82; i1164++) {
            bArr[1166] = (byte) i1164;
            if (sha1_32(bArr) == -1074065052) {
                break;
            }
        }
        for (int i1165 = -23; i1165 < -8; i1165++) {
            bArr[1167] = (byte) i1165;
            if (sha1_32(bArr) == 1464104775) {
                break;
            }
        }
        for (int i1166 = 29; i1166 < 47; i1166++) {
            bArr[1168] = (byte) i1166;
            if (sha1_32(bArr) == 1907519115) {
                break;
            }
        }
        for (int i1167 = 45; i1167 < 66; i1167++) {
            bArr[1169] = (byte) i1167;
            if (sha1_32(bArr) == 1901343085) {
                break;
            }
        }
        for (int i1168 = 106; i1168 < 127; i1168++) {
            bArr[1170] = (byte) i1168;
            if (sha1_32(bArr) == 1010977804) {
                break;
            }
        }
        for (int i1169 = -74; i1169 < -58; i1169++) {
            bArr[1171] = (byte) i1169;
            if (sha1_32(bArr) == 250679162) {
                break;
            }
        }
        for (int i1170 = 48; i1170 < 67; i1170++) {
            bArr[1172] = (byte) i1170;
            if (sha1_32(bArr) == 1225134169) {
                break;
            }
        }
        for (int i1171 = 66; i1171 < 82; i1171++) {
            bArr[1173] = (byte) i1171;
            if (sha1_32(bArr) == -1963106594) {
                break;
            }
        }
        for (int i1172 = -52; i1172 < -31; i1172++) {
            bArr[1174] = (byte) i1172;
            if (sha1_32(bArr) == -1519136762) {
                break;
            }
        }
        for (int i1173 = -54; i1173 < -36; i1173++) {
            bArr[1175] = (byte) i1173;
            if (sha1_32(bArr) == 398221438) {
                break;
            }
        }
        for (int i1174 = -20; i1174 < -10; i1174++) {
            bArr[1176] = (byte) i1174;
            if (sha1_32(bArr) == 432397359) {
                break;
            }
        }
        for (int i1175 = -9; i1175 < 13; i1175++) {
            bArr[1177] = (byte) i1175;
            if (sha1_32(bArr) == 1501156403) {
                break;
            }
        }
        for (int i1176 = -65; i1176 < -58; i1176++) {
            bArr[1178] = (byte) i1176;
            if (sha1_32(bArr) == -1487434331) {
                break;
            }
        }
        for (int i1177 = 11; i1177 < 27; i1177++) {
            bArr[1179] = (byte) i1177;
            if (sha1_32(bArr) == -207114850) {
                break;
            }
        }
        for (int i1178 = -119; i1178 < -97; i1178++) {
            bArr[1180] = (byte) i1178;
            if (sha1_32(bArr) == -1648408933) {
                break;
            }
        }
        for (int i1179 = 33; i1179 < 50; i1179++) {
            bArr[1181] = (byte) i1179;
            if (sha1_32(bArr) == 1296737303) {
                break;
            }
        }
        for (int i1180 = 80; i1180 < 99; i1180++) {
            bArr[1182] = (byte) i1180;
            if (sha1_32(bArr) == -579030670) {
                break;
            }
        }
        for (int i1181 = 31; i1181 < 39; i1181++) {
            bArr[1183] = (byte) i1181;
            if (sha1_32(bArr) == -1172334507) {
                break;
            }
        }
        for (int i1182 = 76; i1182 < 95; i1182++) {
            bArr[1184] = (byte) i1182;
            if (sha1_32(bArr) == 1533639366) {
                break;
            }
        }
        for (int i1183 = -19; i1183 < -16; i1183++) {
            bArr[1185] = (byte) i1183;
            if (sha1_32(bArr) == 1007157596) {
                break;
            }
        }
        for (int i1184 = -87; i1184 < -71; i1184++) {
            bArr[1186] = (byte) i1184;
            if (sha1_32(bArr) == -2083732269) {
                break;
            }
        }
        for (int i1185 = -47; i1185 < -25; i1185++) {
            bArr[1187] = (byte) i1185;
            if (sha1_32(bArr) == 740543526) {
                break;
            }
        }
        for (int i1186 = ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE; i1186 < 116; i1186++) {
            bArr[1188] = (byte) i1186;
            if (sha1_32(bArr) == -189424482) {
                break;
            }
        }
        for (int i1187 = -46; i1187 < -31; i1187++) {
            bArr[1189] = (byte) i1187;
            if (sha1_32(bArr) == 1541386841) {
                break;
            }
        }
        for (int i1188 = -111; i1188 < -94; i1188++) {
            bArr[1190] = (byte) i1188;
            if (sha1_32(bArr) == -961777041) {
                break;
            }
        }
        for (int i1189 = 63; i1189 < 76; i1189++) {
            bArr[1191] = (byte) i1189;
            if (sha1_32(bArr) == 96741325) {
                break;
            }
        }
        for (int i1190 = -126; i1190 < -111; i1190++) {
            bArr[1192] = (byte) i1190;
            if (sha1_32(bArr) == 410075310) {
                break;
            }
        }
        for (int i1191 = -39; i1191 < -24; i1191++) {
            bArr[1193] = (byte) i1191;
            if (sha1_32(bArr) == 914264809) {
                break;
            }
        }
        for (int i1192 = 58; i1192 < 76; i1192++) {
            bArr[1194] = (byte) i1192;
            if (sha1_32(bArr) == 1644878320) {
                break;
            }
        }
        for (int i1193 = 60; i1193 < 76; i1193++) {
            bArr[1195] = (byte) i1193;
            if (sha1_32(bArr) == 1423853676) {
                break;
            }
        }
        for (int i1194 = -34; i1194 < -26; i1194++) {
            bArr[1196] = (byte) i1194;
            if (sha1_32(bArr) == -1294893272) {
                break;
            }
        }
        for (int i1195 = -111; i1195 < -99; i1195++) {
            bArr[1197] = (byte) i1195;
            if (sha1_32(bArr) == -1748085153) {
                break;
            }
        }
        for (int i1196 = 82; i1196 < 95; i1196++) {
            bArr[1198] = (byte) i1196;
            if (sha1_32(bArr) == 369663221) {
                break;
            }
        }
        for (int i1197 = 115; i1197 < 122; i1197++) {
            bArr[1199] = (byte) i1197;
            if (sha1_32(bArr) == -733587066) {
                break;
            }
        }
        for (int i1198 = 99; i1198 < 124; i1198++) {
            bArr[1200] = (byte) i1198;
            if (sha1_32(bArr) == 1196897535) {
                break;
            }
        }
        for (int i1199 = 66; i1199 < 76; i1199++) {
            bArr[1201] = (byte) i1199;
            if (sha1_32(bArr) == -2032286769) {
                break;
            }
        }
        for (int i1200 = 18; i1200 < 40; i1200++) {
            bArr[1202] = (byte) i1200;
            if (sha1_32(bArr) == -1316446387) {
                break;
            }
        }
        for (int i1201 = -65; i1201 < -49; i1201++) {
            bArr[1203] = (byte) i1201;
            if (sha1_32(bArr) == 789821022) {
                break;
            }
        }
        for (int i1202 = 74; i1202 < 91; i1202++) {
            bArr[1204] = (byte) i1202;
            if (sha1_32(bArr) == 450238142) {
                break;
            }
        }
        for (int i1203 = -88; i1203 < -86; i1203++) {
            bArr[1205] = (byte) i1203;
            if (sha1_32(bArr) == 1153848280) {
                break;
            }
        }
        for (i4 = -71; i4 < -61; i4++) {
            bArr[1206] = (byte) i4;
            if (sha1_32(bArr) == 74398518) {
                break;
            }
        }
        for (int i1204 = 60; i1204 < 84; i1204++) {
            bArr[1207] = (byte) i1204;
            if (sha1_32(bArr) == -1367451349) {
                break;
            }
        }
        for (int i1205 = -110; i1205 < -104; i1205++) {
            bArr[1208] = (byte) i1205;
            if (sha1_32(bArr) == 716351476) {
                break;
            }
        }
        for (int i1206 = -39; i1206 < -28; i1206++) {
            bArr[1209] = (byte) i1206;
            if (sha1_32(bArr) == -1555172217) {
                break;
            }
        }
        for (int i1207 = -27; i1207 < -8; i1207++) {
            bArr[1210] = (byte) i1207;
            if (sha1_32(bArr) == -152588912) {
                break;
            }
        }
        for (int i1208 = -30; i1208 < -4; i1208++) {
            bArr[1211] = (byte) i1208;
            if (sha1_32(bArr) == -1762768221) {
                break;
            }
        }
        for (int i1209 = 17; i1209 < 38; i1209++) {
            bArr[1212] = (byte) i1209;
            if (sha1_32(bArr) == 20225977) {
                break;
            }
        }
        for (int i1210 = -128; i1210 < -122; i1210++) {
            bArr[1213] = (byte) i1210;
            if (sha1_32(bArr) == 473645896) {
                break;
            }
        }
        for (int i1211 = 7; i1211 < 28; i1211++) {
            bArr[1214] = (byte) i1211;
            if (sha1_32(bArr) == -1406643193) {
                break;
            }
        }
        for (int i1212 = -36; i1212 < -22; i1212++) {
            bArr[1215] = (byte) i1212;
            if (sha1_32(bArr) == 217511625) {
                break;
            }
        }
        for (int i1213 = -118; i1213 < -89; i1213++) {
            bArr[1216] = (byte) i1213;
            if (sha1_32(bArr) == 645043409) {
                break;
            }
        }
        for (int i1214 = 69; i1214 < 86; i1214++) {
            bArr[1217] = (byte) i1214;
            if (sha1_32(bArr) == 1225765376) {
                break;
            }
        }
        for (int i1215 = 29; i1215 < 50; i1215++) {
            bArr[1218] = (byte) i1215;
            if (sha1_32(bArr) == 1086871078) {
                break;
            }
        }
        for (int i1216 = -34; i1216 < -11; i1216++) {
            bArr[1219] = (byte) i1216;
            if (sha1_32(bArr) == -397828006) {
                break;
            }
        }
        for (int i1217 = UiPosIndexEnum.HOME_COUNTDOWN; i1217 < 128; i1217++) {
            bArr[1220] = (byte) i1217;
            if (sha1_32(bArr) == -265220235) {
                break;
            }
        }
        for (int i1218 = -113; i1218 < -103; i1218++) {
            bArr[1221] = (byte) i1218;
            if (sha1_32(bArr) == 311137741) {
                break;
            }
        }
        for (int i1219 = -9; i1219 < 19; i1219++) {
            bArr[1222] = (byte) i1219;
            if (sha1_32(bArr) == -1343638975) {
                break;
            }
        }
        for (int i1220 = -29; i1220 < -9; i1220++) {
            bArr[1223] = (byte) i1220;
            if (sha1_32(bArr) == -1475468804) {
                break;
            }
        }
        for (int i1221 = -27; i1221 < -9; i1221++) {
            bArr[1224] = (byte) i1221;
            if (sha1_32(bArr) == -807217792) {
                break;
            }
        }
        for (int i1222 = -38; i1222 < -22; i1222++) {
            bArr[1225] = (byte) i1222;
            if (sha1_32(bArr) == -999187897) {
                break;
            }
        }
        for (int i1223 = 24; i1223 < 33; i1223++) {
            bArr[1226] = (byte) i1223;
            if (sha1_32(bArr) == -1746989543) {
                break;
            }
        }
        for (int i1224 = -106; i1224 < -87; i1224++) {
            bArr[1227] = (byte) i1224;
            if (sha1_32(bArr) == 1253467710) {
                break;
            }
        }
        for (int i1225 = 95; i1225 < 115; i1225++) {
            bArr[1228] = (byte) i1225;
            if (sha1_32(bArr) == 1244404312) {
                break;
            }
        }
        for (int i1226 = -126; i1226 < -107; i1226++) {
            bArr[1229] = (byte) i1226;
            if (sha1_32(bArr) == 414453749) {
                break;
            }
        }
        for (int i1227 = -128; i1227 < -106; i1227++) {
            bArr[1230] = (byte) i1227;
            if (sha1_32(bArr) == 1958596878) {
                break;
            }
        }
        for (int i1228 = -59; i1228 < -38; i1228++) {
            bArr[1231] = (byte) i1228;
            if (sha1_32(bArr) == -558078193) {
                break;
            }
        }
        for (int i1229 = 91; i1229 < 109; i1229++) {
            bArr[1232] = (byte) i1229;
            if (sha1_32(bArr) == -952590520) {
                break;
            }
        }
        for (int i1230 = 50; i1230 < 72; i1230++) {
            bArr[1233] = (byte) i1230;
            if (sha1_32(bArr) == 1764136954) {
                break;
            }
        }
        for (int i1231 = -59; i1231 < -48; i1231++) {
            bArr[1234] = (byte) i1231;
            if (sha1_32(bArr) == 283179825) {
                break;
            }
        }
        for (int i1232 = -35; i1232 < -13; i1232++) {
            bArr[1235] = (byte) i1232;
            if (sha1_32(bArr) == 293899202) {
                break;
            }
        }
        for (int i1233 = -64; i1233 < -35; i1233++) {
            bArr[1236] = (byte) i1233;
            if (sha1_32(bArr) == 470158912) {
                break;
            }
        }
        for (int i1234 = 65; i1234 < 80; i1234++) {
            bArr[1237] = (byte) i1234;
            if (sha1_32(bArr) == -1019789848) {
                break;
            }
        }
        for (int i1235 = -25; i1235 < -7; i1235++) {
            bArr[1238] = (byte) i1235;
            if (sha1_32(bArr) == 1841575509) {
                break;
            }
        }
        for (int i1236 = -66; i1236 < -55; i1236++) {
            bArr[1239] = (byte) i1236;
            if (sha1_32(bArr) == 561944749) {
                break;
            }
        }
        for (int i1237 = -102; i1237 < -81; i1237++) {
            bArr[1240] = (byte) i1237;
            if (sha1_32(bArr) == 1305584918) {
                break;
            }
        }
        for (int i1238 = -7; i1238 < 21; i1238++) {
            bArr[1241] = (byte) i1238;
            if (sha1_32(bArr) == 458851520) {
                break;
            }
        }
        for (int i1239 = -46; i1239 < -38; i1239++) {
            bArr[1242] = (byte) i1239;
            if (sha1_32(bArr) == -975645795) {
                break;
            }
        }
        for (int i1240 = -24; i1240 < -18; i1240++) {
            bArr[1243] = (byte) i1240;
            if (sha1_32(bArr) == 1034706741) {
                break;
            }
        }
        for (i2 = 109; i2 < 122; i2++) {
            bArr[1244] = (byte) i2;
            if (sha1_32(bArr) == -44026232) {
                break;
            }
        }
        for (int i1241 = -61; i1241 < -38; i1241++) {
            bArr[1245] = (byte) i1241;
            if (sha1_32(bArr) == -1636811415) {
                break;
            }
        }
        for (int i1242 = 92; i1242 < 103; i1242++) {
            bArr[1246] = (byte) i1242;
            if (sha1_32(bArr) == 1150841321) {
                break;
            }
        }
        for (int i1243 = 81; i1243 < 108; i1243++) {
            bArr[1247] = (byte) i1243;
            if (sha1_32(bArr) == 1379943001) {
                break;
            }
        }
        for (int i1244 = 59; i1244 < 77; i1244++) {
            bArr[1248] = (byte) i1244;
            if (sha1_32(bArr) == -949711030) {
                break;
            }
        }
        for (int i1245 = 69; i1245 < 79; i1245++) {
            bArr[1249] = (byte) i1245;
            if (sha1_32(bArr) == -173040283) {
                break;
            }
        }
        for (int i1246 = 117; i1246 < 128; i1246++) {
            bArr[1250] = (byte) i1246;
            if (sha1_32(bArr) == 332447459) {
                break;
            }
        }
        for (int i1247 = 40; i1247 < 62; i1247++) {
            bArr[1251] = (byte) i1247;
            if (sha1_32(bArr) == 1315791753) {
                break;
            }
        }
        for (int i1248 = 22; i1248 < 26; i1248++) {
            bArr[1252] = (byte) i1248;
            if (sha1_32(bArr) == -2031948247) {
                break;
            }
        }
        for (int i1249 = -12; i1249 < 5; i1249++) {
            bArr[1253] = (byte) i1249;
            if (sha1_32(bArr) == 1958528937) {
                break;
            }
        }
        for (int i1250 = 58; i1250 < 81; i1250++) {
            bArr[1254] = (byte) i1250;
            if (sha1_32(bArr) == 745492092) {
                break;
            }
        }
        for (int i1251 = -99; i1251 < -82; i1251++) {
            bArr[1255] = (byte) i1251;
            if (sha1_32(bArr) == 339177632) {
                break;
            }
        }
        for (i3 = 63; i3 < 82; i3++) {
            bArr[1256] = (byte) i3;
            if (sha1_32(bArr) == 168721533) {
                break;
            }
        }
        for (int i1252 = 31; i1252 < 51; i1252++) {
            bArr[1257] = (byte) i1252;
            if (sha1_32(bArr) == -414738466) {
                break;
            }
        }
        for (int i1253 = -21; i1253 < -1; i1253++) {
            bArr[1258] = (byte) i1253;
            if (sha1_32(bArr) == -494846405) {
                break;
            }
        }
        for (int i1254 = 69; i1254 < 97; i1254++) {
            bArr[1259] = (byte) i1254;
            if (sha1_32(bArr) == -711689673) {
                break;
            }
        }
        for (int i1255 = 19; i1255 < 29; i1255++) {
            bArr[1260] = (byte) i1255;
            if (sha1_32(bArr) == -1155308829) {
                break;
            }
        }
        for (int i1256 = -48; i1256 < -23; i1256++) {
            bArr[1261] = (byte) i1256;
            if (sha1_32(bArr) == 892835085) {
                break;
            }
        }
        for (int i1257 = -92; i1257 < -73; i1257++) {
            bArr[1262] = (byte) i1257;
            if (sha1_32(bArr) == -1363851510) {
                break;
            }
        }
        for (int i1258 = 7; i1258 < 9; i1258++) {
            bArr[1263] = (byte) i1258;
            if (sha1_32(bArr) == -1052426185) {
                break;
            }
        }
        for (int i1259 = 18; i1259 < 33; i1259++) {
            bArr[1264] = (byte) i1259;
            if (sha1_32(bArr) == -561645499) {
                break;
            }
        }
        for (int i1260 = 16; i1260 < 35; i1260++) {
            bArr[1265] = (byte) i1260;
            if (sha1_32(bArr) == 2062264690) {
                break;
            }
        }
        for (int i1261 = 96; i1261 < 112; i1261++) {
            bArr[1266] = (byte) i1261;
            if (sha1_32(bArr) == 410848313) {
                break;
            }
        }
        for (int i1262 = 70; i1262 < 91; i1262++) {
            bArr[1267] = (byte) i1262;
            if (sha1_32(bArr) == -887593500) {
                break;
            }
        }
        for (int i1263 = 93; i1263 < 106; i1263++) {
            bArr[1268] = (byte) i1263;
            if (sha1_32(bArr) == 504128351) {
                break;
            }
        }
        for (int i1264 = -114; i1264 < -103; i1264++) {
            bArr[1269] = (byte) i1264;
            if (sha1_32(bArr) == 1778662523) {
                break;
            }
        }
        for (i = 27; i < 49; i++) {
            bArr[1270] = (byte) i;
            if (sha1_32(bArr) == -1775645409) {
                break;
            }
        }
        for (int i1265 = -115; i1265 < -104; i1265++) {
            bArr[1271] = (byte) i1265;
            if (sha1_32(bArr) == 606604150) {
                break;
            }
        }
        for (int i1266 = 92; i1266 < 115; i1266++) {
            bArr[1272] = (byte) i1266;
            if (sha1_32(bArr) == 1219857337) {
                break;
            }
        }
        for (int i1267 = 70; i1267 < 96; i1267++) {
            bArr[1273] = (byte) i1267;
            if (sha1_32(bArr) == 906819558) {
                break;
            }
        }
        for (int i1268 = 36; i1268 < 51; i1268++) {
            bArr[1274] = (byte) i1268;
            if (sha1_32(bArr) == -1772536995) {
                break;
            }
        }
        return bArr;
    }
}
