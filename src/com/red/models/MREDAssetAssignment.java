package com.red.models;

import java.sql.ResultSet;
import java.util.Properties;

public class MREDAssetAssignment extends X_RED_Asset_Assignment {
	
	private static final long serialVersionUID = -3331417422921354934L;

	public MREDAssetAssignment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MREDAssetAssignment(Properties ctx, int RED_Asset_Assignment_ID, String trxName) {
		super(ctx, RED_Asset_Assignment_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
