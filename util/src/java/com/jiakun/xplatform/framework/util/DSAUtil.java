package com.jiakun.xplatform.framework.util;

import com.alibaba.service.dsa.util.DSAKeyPairGenerator;

public class DSAUtil {

	public static void main(String[] args) {

		try {
			String[] arg0 = new String[2];
			arg0[0] = "pubkey.dat";
			arg0[1] = "prikey.dat";
			DSAKeyPairGenerator.main(arg0);
		} catch (Exception e) {

		}
	}

}
