package com.red.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.red.processes.AssignToProcess;

public class AssignToProcessFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		// TODO Auto-generated method stub
		
		if (className.equals("com.red.processes.AssignToProcess"))
			return new AssignToProcess();
		
		return null;
	}

}
