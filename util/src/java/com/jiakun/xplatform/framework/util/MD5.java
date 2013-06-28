/************************************************
 * MD5 �㷨��Java Bean
 * 
 * @author:Topcat Tuppin
 *                Last Modified:10,Mar,2001
 *************************************************/
package com.jiakun.xplatform.framework.util;

import java.lang.reflect.Array;

import org.apache.log4j.Logger;

/*************************************************
 * md5 ��ʵ����RSA Data Security, Inc.���ύ��IETF ��RFC1321�е�MD5 message-digest
 * �㷨��
 *************************************************/
public class MD5 {

	/*
	 * ������ЩS11-S44ʵ������һ��4*4�ľ�����ԭʼ��Cʵ��������#define ʵ�ֵģ�
	 * ���������ʵ�ֳ�Ϊstatic final�Ǳ�ʾ��ֻ����������ͬһ����̿ռ��ڵĶ�� Instance�乲��
	 */
	static final int S11 = 7;
	static final int S12 = 12;
	static final int S13 = 17;
	static final int S14 = 22;

	static final int S21 = 5;
	static final int S22 = 9;
	static final int S23 = 14;
	static final int S24 = 20;

	static final int S31 = 4;
	static final int S32 = 11;
	static final int S33 = 16;
	static final int S34 = 23;

	static final int S41 = 6;
	static final int S42 = 10;
	static final int S43 = 15;
	static final int S44 = 21;

	static final byte[] PADDING = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final Logger logger = Logger.getLogger(MD5.class);

	/*
	 * ����������Ա��MD5���������õ���3��������ݣ���ԭʼ��Cʵ���� �����嵽MD5_CTX�ṹ��
	 */
	// state (ABCD)
	private long[] state = new long[4];

	// number of bits, modulo 2^64 (lsb first)
	private long[] count = new long[2];

	// input buffer
	private byte[] buffer = new byte[64];

	/*
	 * digest,������һ�μ������2�����ڲ���ʾ����ʾ128bit��MD5ֵ.
	 */
	private byte[] digest = new byte[16];

	// ����MD5�����ı�׼���캯��JavaBeanҪ����һ��public�Ĳ���û�в���Ĺ��캯��
	public MD5() {
		md5Init();
		return;
	}

	/*
	 * getMD5ofStr����MD5����Ҫ�Ĺ�����������ڲ���������Ҫ����MD5�任���ַ�
	 * ���ص��Ǳ任��Ľ���������Ǵӹ�����ԱdigestHexStrȡ�õģ�
	 */
	public String getMD5ofStr(String inbuf) {
		md5Init();
		md5Update(inbuf.getBytes(), inbuf.length());
		md5Final();
		StringBuilder digestHexStr = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			digestHexStr.append(byteHEX(digest[i]));
		}

		return digestHexStr.toString();
	}

	/* md5Init��һ����ʼ�������ʼ�����ı�����װ���׼�Ļ��� */
	private void md5Init() {
		count[0] = 0L;
		count[1] = 0L;
		// /* Load magic initialization constants.

		state[0] = 0x67452301L;
		state[1] = 0xefcdab89L;
		state[2] = 0x98badcfeL;
		state[3] = 0x10325476L;

		return;
	}

	/*
	 * F, G, H ,I ��4�����MD5������ԭʼ��MD5��Cʵ���У�����������
	 * �򵥵�λ���㣬���ܳ���Ч�ʵĿ��ǰ�����ʵ�ֳ��˺꣬��java�У����ǰ�����
	 * ����ʵ�ֳ���private���������ֱ�����ԭ��C�еġ�
	 */

	private long f(long x, long y, long z) {
		return (x & y) | ((~x) & z);

	}

	private long g(long x, long y, long z) {
		return (x & z) | (y & (~z));

	}

	private long h(long x, long y, long z) {
		return x ^ y ^ z;
	}

	private long i(long x, long y, long z) {
		return y ^ (x | (~z));
	}

	/*
	 * FF,GG,HH��II������F,G,H,I���н�һ���任 FF, GG, HH, and II transformations
	 * for rounds 1, 2, 3, and 4. Rotation is separate from addition to prevent
	 * recomputation.
	 */

	private long ff(long a, long b, long c, long d, long x, long s, long ac) {
		a += f(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));
		a += b;
		return a;
	}

	private long gg(long a, long b, long c, long d, long x, long s, long ac) {
		a += g(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));
		a += b;
		return a;
	}

	private long hh(long a, long b, long c, long d, long x, long s, long ac) {
		a += h(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));
		a += b;
		return a;
	}

	private long ii(long a, long b, long c, long d, long x, long s, long ac) {
		a += i(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));
		a += b;
		return a;
	}

	/*
	 * md5Update��MD5���������̣�inbuf��Ҫ�任���ֽڴ���inputlen�ǳ��ȣ����
	 * ������getMD5ofStr���ã�����֮ǰ��Ҫ����md5init����˰�����Ƴ�private��
	 */
	private void md5Update(byte[] inbuf, int inputLen) {

		int i;
		int index;
		int partLen;

		byte[] block = new byte[64];
		index = (int) (count[0] >>> 3) & 0x3F;
		// /* Update number of bits */
		if ((count[0] += (inputLen << 3)) < (inputLen << 3)) {
			count[1]++;
		}
		count[1] += (inputLen >>> 29);

		partLen = 64 - index;

		// Transform as many times as possible.
		if (inputLen >= partLen) {
			md5Memcpy(buffer, inbuf, index, 0, partLen);
			md5Transform(buffer);

			for (i = partLen; i + 63 < inputLen; i += 64) {

				md5Memcpy(block, inbuf, 0, i, 64);
				md5Transform(block);
			}
			index = 0;

		} else {
			i = 0;
		}
		// /* Buffer remaining input */
		md5Memcpy(buffer, inbuf, index, i, inputLen - i);

	}

	/*
	 * md5Final�������д������
	 */
	private void md5Final() {
		byte[] bits = new byte[8];
		int index;
		int padLen;

		// /* Save number of bits */
		encode(bits, count, 8);

		// /* Pad out to 56 mod 64.
		index = (int) (count[0] >>> 3) & 0x3f;
		padLen = (index < 56) ? (56 - index) : (120 - index);
		md5Update(PADDING, padLen);

		// /* Append length (before padding) */
		md5Update(bits, 8);

		// /* Store state in digest */
		encode(digest, state, 16);

	}

	/*
	 * md5Memcpy��һ���ڲ�ʹ�õ�byte����Ŀ鿽�������input��inpos��ʼ��len���ȵ�
	 * ���������� �ֽڿ�����output��outposλ�ÿ�ʼ
	 */

	private void md5Memcpy(byte[] output, byte[] input, int outpos, int inpos, int len) {
		int i;

		for (i = 0; i < len; i++) {
			output[outpos + i] = input[inpos + i];
		}
	}

	/*
	 * md5Transform��MD5���ı任������md5Update���ã�block�Ƿֿ��ԭʼ�ֽ�
	 */
	private void md5Transform(byte[] block) {
		long a = state[0];
		long b = state[1];
		long c = state[2];
		long d = state[3];

		long[] x = new long[16];

		decode(x, block, 64);

		/* Round 1 */
		/* 1 */
		a = ff(a, b, c, d, x[0], S11, 0xd76aa478L);
		/* 2 */
		d = ff(d, a, b, c, x[1], S12, 0xe8c7b756L);
		/* 3 */
		c = ff(c, d, a, b, x[2], S13, 0x242070dbL);
		/* 4 */
		b = ff(b, c, d, a, x[3], S14, 0xc1bdceeeL);
		/* 5 */
		a = ff(a, b, c, d, x[4], S11, 0xf57c0fafL);
		/* 6 */
		d = ff(d, a, b, c, x[5], S12, 0x4787c62aL);
		/* 7 */
		c = ff(c, d, a, b, x[6], S13, 0xa8304613L);
		/* 8 */
		b = ff(b, c, d, a, x[7], S14, 0xfd469501L);
		/* 9 */
		a = ff(a, b, c, d, x[8], S11, 0x698098d8L);
		/* 10 */
		d = ff(d, a, b, c, x[9], S12, 0x8b44f7afL);
		/* 11 */
		c = ff(c, d, a, b, x[10], S13, 0xffff5bb1L);
		/* 12 */
		b = ff(b, c, d, a, x[11], S14, 0x895cd7beL);
		/* 13 */
		a = ff(a, b, c, d, x[12], S11, 0x6b901122L);
		/* 14 */
		d = ff(d, a, b, c, x[13], S12, 0xfd987193L);
		/* 15 */
		c = ff(c, d, a, b, x[14], S13, 0xa679438eL);
		/* 16 */
		b = ff(b, c, d, a, x[15], S14, 0x49b40821L);

		/* Round 2 */
		/* 17 */
		a = gg(a, b, c, d, x[1], S21, 0xf61e2562L);
		/* 18 */
		d = gg(d, a, b, c, x[6], S22, 0xc040b340L);
		/* 19 */
		c = gg(c, d, a, b, x[11], S23, 0x265e5a51L);
		/* 20 */
		b = gg(b, c, d, a, x[0], S24, 0xe9b6c7aaL);
		/* 21 */
		a = gg(a, b, c, d, x[5], S21, 0xd62f105dL);
		/* 22 */
		d = gg(d, a, b, c, x[10], S22, 0x2441453L);
		/* 23 */
		c = gg(c, d, a, b, x[15], S23, 0xd8a1e681L);
		/* 24 */
		b = gg(b, c, d, a, x[4], S24, 0xe7d3fbc8L);
		/* 25 */
		a = gg(a, b, c, d, x[9], S21, 0x21e1cde6L);
		/* 26 */
		d = gg(d, a, b, c, x[14], S22, 0xc33707d6L);
		/* 27 */
		c = gg(c, d, a, b, x[3], S23, 0xf4d50d87L);
		/* 28 */
		b = gg(b, c, d, a, x[8], S24, 0x455a14edL);
		/* 29 */
		a = gg(a, b, c, d, x[13], S21, 0xa9e3e905L);
		/* 30 */
		d = gg(d, a, b, c, x[2], S22, 0xfcefa3f8L);
		/* 31 */
		c = gg(c, d, a, b, x[7], S23, 0x676f02d9L);
		/* 32 */
		b = gg(b, c, d, a, x[12], S24, 0x8d2a4c8aL);

		/* Round 3 */
		/* 33 */
		a = hh(a, b, c, d, x[5], S31, 0xfffa3942L);
		/* 34 */
		d = hh(d, a, b, c, x[8], S32, 0x8771f681L);
		/* 35 */
		c = hh(c, d, a, b, x[11], S33, 0x6d9d6122L);
		/* 36 */
		b = hh(b, c, d, a, x[14], S34, 0xfde5380cL);
		/* 37 */
		a = hh(a, b, c, d, x[1], S31, 0xa4beea44L);
		/* 38 */
		d = hh(d, a, b, c, x[4], S32, 0x4bdecfa9L);
		/* 39 */
		c = hh(c, d, a, b, x[7], S33, 0xf6bb4b60L);
		/* 40 */
		b = hh(b, c, d, a, x[10], S34, 0xbebfbc70L);
		/* 41 */
		a = hh(a, b, c, d, x[13], S31, 0x289b7ec6L);
		/* 42 */
		d = hh(d, a, b, c, x[0], S32, 0xeaa127faL);
		/* 43 */
		c = hh(c, d, a, b, x[3], S33, 0xd4ef3085L);
		/* 44 */
		b = hh(b, c, d, a, x[6], S34, 0x4881d05L);
		/* 45 */
		a = hh(a, b, c, d, x[9], S31, 0xd9d4d039L);
		/* 46 */
		d = hh(d, a, b, c, x[12], S32, 0xe6db99e5L);
		/* 47 */
		c = hh(c, d, a, b, x[15], S33, 0x1fa27cf8L);
		/* 48 */
		b = hh(b, c, d, a, x[2], S34, 0xc4ac5665L);

		/* Round 4 */
		/* 49 */
		a = ii(a, b, c, d, x[0], S41, 0xf4292244L);
		/* 50 */
		d = ii(d, a, b, c, x[7], S42, 0x432aff97L);
		/* 51 */
		c = ii(c, d, a, b, x[14], S43, 0xab9423a7L);
		/* 52 */
		b = ii(b, c, d, a, x[5], S44, 0xfc93a039L);
		/* 53 */
		a = ii(a, b, c, d, x[12], S41, 0x655b59c3L);
		/* 54 */
		d = ii(d, a, b, c, x[3], S42, 0x8f0ccc92L);
		/* 55 */
		c = ii(c, d, a, b, x[10], S43, 0xffeff47dL);
		/* 56 */
		b = ii(b, c, d, a, x[1], S44, 0x85845dd1L);
		/* 57 */
		a = ii(a, b, c, d, x[8], S41, 0x6fa87e4fL);
		/* 58 */
		d = ii(d, a, b, c, x[15], S42, 0xfe2ce6e0L);
		/* 59 */
		c = ii(c, d, a, b, x[6], S43, 0xa3014314L);
		/* 60 */
		b = ii(b, c, d, a, x[13], S44, 0x4e0811a1L);
		/* 61 */
		a = ii(a, b, c, d, x[4], S41, 0xf7537e82L);
		/* 62 */
		d = ii(d, a, b, c, x[11], S42, 0xbd3af235L);
		/* 63 */
		c = ii(c, d, a, b, x[2], S43, 0x2ad7d2bbL);
		/* 64 */
		b = ii(b, c, d, a, x[9], S44, 0xeb86d391L);

		state[0] += a;
		state[1] += b;
		state[2] += c;
		state[3] += d;

	}

	/*
	 * Encode��long���鰴˳����byte���飬��Ϊjava��long������64bit�ģ�
	 * ֻ���32bit������ӦԭʼCʵ�ֵ���;
	 */
	private void encode(byte[] output, long[] input, int len) {
		int i;
		int j;

		for (i = 0, j = 0; j < len; i++, j += 4) {
			output[j] = (byte) (input[i] & 0xffL);
			output[j + 1] = (byte) ((input[i] >>> 8) & 0xffL);
			output[j + 2] = (byte) ((input[i] >>> 16) & 0xffL);
			output[j + 3] = (byte) ((input[i] >>> 24) & 0xffL);
		}
	}

	/*
	 * Decode��byte���鰴˳��ϳɳ�long���飬��Ϊjava��long������64bit�ģ�
	 * ֻ�ϳɵ�32bit����32bit���㣬����ӦԭʼCʵ�ֵ���;
	 */
	private void decode(long[] output, byte[] input, int len) {
		int i;
		int j;

		for (i = 0, j = 0; j < len; i++, j += 4) {
			output[i] =
				b2iu(input[j]) | (b2iu(input[j + 1]) << 8) | (b2iu(input[j + 2]) << 16) | (b2iu(input[j + 3]) << 24);
		}

		return;
	}

	/*
	 * b2iu����д��һ����byte���ղ�������ŵ�ԭ��ģ���λ��������Ϊjavaû��unsigned����
	 */
	public static long b2iu(byte b) {
		return b < 0 ? b & 0x7F + 128 : b;
	}

	/*
	 * byteHEX()��������һ��byte���͵���ת����ʮ����Ƶ�ASCII��ʾ��
	 * ����Ϊjava�е�byte��toString�޷�ʵ����һ�㣬������û��C�����е�
	 * sprintf(outbuf,"%02X",ib)
	 */
	public static String byteHEX(byte ib) {
		char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = digit[(ib >>> 4) & 0X0F];
		ob[1] = digit[ib & 0X0F];
		return new String(ob);
	}

	public static void main(String[] args) {
		MD5 m = new MD5();
		if (Array.getLength(args) == 0) {
			logger.info("MD5(\"111111\"):" + m.getMD5ofStr("111111"));
		} else {
			logger.info("MD5(" + args[0] + ")=" + m.getMD5ofStr(args[0]));
		}
	}

}
