package com.red.processes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAsset;
import org.compiere.model.MUser;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import com.red.models.MREDAssetAssignment;

public class AssignToProcess extends SvrProcess {
	
	private int A_Asset_ID;
	private int AD_User_ID;
	private int C_Activity_ID;
	private int C_Location_ID;

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		ProcessInfoParameter[] params = getParameter();
		for(ProcessInfoParameter param : params) {
			String paramName = param.getParameterName();
			
			if (paramName.equalsIgnoreCase("AD_User_ID")) {
				AD_User_ID = param.getParameterAsInt();
			} else if(paramName.equalsIgnoreCase("A_Asset_ID")) {
				A_Asset_ID = param.getParameterAsInt();
			} else if(paramName.equalsIgnoreCase("C_Activity_ID")) {
				C_Activity_ID = param.getParameterAsInt();
			} else if(paramName.equalsIgnoreCase("C_Location_ID")) {
				C_Location_ID = param.getParameterAsInt();
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " +  paramName);				
			}
			
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		MAsset asset = new MAsset(null, A_Asset_ID, null);
		MUser user = new MUser(null, AD_User_ID, null);
		MREDAssetAssignment assetAssignment = new MREDAssetAssignment(Env.getCtx(), 0, null);
		
		try {
			
			asset.setAD_User_ID(AD_User_ID);
			asset.saveEx();
			
			assetAssignment.setA_Asset_ID(A_Asset_ID);
			assetAssignment.setC_Activity_ID(C_Activity_ID);
			assetAssignment.setC_Location_ID(C_Location_ID);
			assetAssignment.setAssigment_Date(new Timestamp(System.currentTimeMillis()));
			assetAssignment.setIsAssigned(true);
			assetAssignment.setAD_User_ID(AD_User_ID);
			assetAssignment.setName(user.getName());
			assetAssignment.setAD_Org_ID(user.getAD_Org_ID());
			assetAssignment.saveEx();

			addLog(getProcessInfo().getAD_Process_ID(), new Timestamp(System.currentTimeMillis()), new BigDecimal(asset.getA_Asset_ID()), "Asset Name : " + asset.getName());
			addLog(getProcessInfo().getAD_Process_ID(), new Timestamp(System.currentTimeMillis()), new BigDecimal(user.getAD_User_ID()), "Assign To : " + user.getName());
			
		} catch (Exception e) {
			
			throw new AdempiereException(e);
			
		}
			
		
		return "Success Assign";
	}
	
}
