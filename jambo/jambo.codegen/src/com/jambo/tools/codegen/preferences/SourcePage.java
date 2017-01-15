package com.jambo.tools.codegen.preferences;

import java.io.File;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jambo.tools.codegen.CodegenPlugin;
import com.jambo.tools.codegen.preferences.editors.ComboFieldEditor;
import com.jambo.tools.codegen.util.ValueStore;



public class SourcePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	DirectoryFieldEditor tgtField;
	DirectoryFieldEditor srcField;
	DirectoryFieldEditor webField;
	DirectoryFieldEditor testField;

	public SourcePage() {
		super(GRID);
		setPreferenceStore(CodegenPlugin.getDefault().getPreferenceStore());
		setDescription("���ô������ɵı���Ŀ¼�Ͳ���");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		tgtField = new DirectoryFieldEditor(PreferenceConstants.TGTFOLDER, "Ŀ���Ŀ¼", getFieldEditorParent());
		tgtField.getTextControl(getFieldEditorParent()).setEditable(false);
		addField(tgtField);
		
		tgtField.getTextControl(getFieldEditorParent()).addModifyListener(new ModifyListener() {    
			  public void modifyText(ModifyEvent arg0) {    
			    setHomePath(arg0);
			  }

			});          
		
		srcField = new DirectoryFieldEditor(PreferenceConstants.SRCFOLDER,"Դ����Ŀ¼", getFieldEditorParent()); 
		srcField.getTextControl(getFieldEditorParent()).setEditable(false);
		addField(srcField);
		
		testField = new DirectoryFieldEditor(PreferenceConstants.TESTFOLDER,"���Դ���Ŀ¼", getFieldEditorParent());
		testField.getTextControl(getFieldEditorParent()).setEditable(false);
		addField(testField);
		
		webField = new DirectoryFieldEditor(PreferenceConstants.WEBFOLDER,"ҳ��Ŀ¼", getFieldEditorParent()); 
		webField.getTextControl(getFieldEditorParent()).setEditable(false);
		addField(webField);

		//2013-4-27 jinbo ������Ŀ����,�������������
		addField(new StringFieldEditor(PreferenceConstants.PROJECT_NAME,
				"��Ŀ����(����)", 40, getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.AUTHOR,
				"������(����ע����)", 40, getFieldEditorParent()));
	}


	private void setHomePath(ModifyEvent arg0) {
	    Text txtCtrl = (Text) arg0.getSource();
	    
	    setPath(srcField.getTextControl(getFieldEditorParent()), txtCtrl.getText() + "\\src");
	    setPath(webField.getTextControl(getFieldEditorParent()), txtCtrl.getText() + "\\web");
	    setPath(testField.getTextControl(getFieldEditorParent()), txtCtrl.getText() + "\\test");
	}    
	
	private void setPath(Text txtCtrl, String path){
        File f = new File(path);
        if (!f.exists()) {
			f.mkdir();
		}
        
        txtCtrl.setText(path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}