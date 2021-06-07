package br.com.zupacademy.templateproposta.propostas;

import java.util.HashMap;
import java.util.Map;

public class MapearStatusProposta {
	
	public static Map<String, StatusProposta> mStatus;
	static {
		mStatus = new HashMap<>();
		mStatus.put("COM_RESTRICAO", StatusProposta.NAO_ELEGIVEL);
		mStatus.put("SEM_RESTRICAO", StatusProposta.ELEGIVEL);
	}

}
