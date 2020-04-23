
package com.nike.app.dq.boot.util.convert;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.nike.app.dq.boot.web.mvc.bean.form.QsRcActionForm;
import com.nike.app.dq.boot.web.mvc.bean.form.R21fcRcActionForm;
import com.nike.app.dq.boot.web.mvc.bean.form.R33mcRcActionForm;
import com.nike.app.dq.boot.web.mvc.bean.form.TcRcActionForm;
import com.nike.app.dq.boot.web.mvc.bean.form.UrlEncodedForm;
import com.nike.app.dq.boot.web.mvc.bean.form.UrlEncodedFormBean;
import com.nike.app.dq.boot.web.mvc.bean.form.UrlEncodedFormFieldMapping;

public class UrlEncodedFormFactory {

	public final static String URL_ENCODED_FORM_BEAN_PATH = "com/nike/app/dq/boot/web/mvc/bean/form/";

	private static UrlEncodedFormFactory instance = null;

	private Map<String, Class<UrlEncodedFormBean>> beanMappings = new HashMap<String, Class<UrlEncodedFormBean>>();
	private Map<String, Map<String, Method>> methodMappings = new HashMap<String, Map<String, Method>>();

	public static UrlEncodedFormFactory getInstance() {
		if (instance == null) {
			instance = new UrlEncodedFormFactory();
		}
		return instance;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized void init() throws ClassNotFoundException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL[] urls = ((URLClassLoader)loader).getURLs();
		for (int i = 0; i < urls.length; i++) {
			String classPathFolder = urls[i].getFile();
			File location = new File(classPathFolder);
			if (location.isDirectory()) {
				String formClassPath = classPathFolder + URL_ENCODED_FORM_BEAN_PATH;
				File path = new File(formClassPath);
				File[] files = path.listFiles();
				for (File file : files) {
					String classFileName = file.getName();
					if (classFileName.endsWith(".class")) {
						String name = URL_ENCODED_FORM_BEAN_PATH + classFileName;
						String externalName = name.substring(0, name.indexOf('.')).replace('/', '.');
						Class uninitializedClass = Class.forName(externalName, false, loader);
						if (UrlEncodedFormBean.class.isAssignableFrom(uninitializedClass)) {
							Class<UrlEncodedFormBean> clazz = UrlEncodedFormBean.class.getClass().cast(Class.forName(externalName));
							UrlEncodedForm form = clazz.getAnnotation(UrlEncodedForm.class);
							if (form != null) {
								beanMappings.put(form.value(), clazz);
								Map<String, Method> methodMapping = new HashMap<String, Method>();
								Method[] methods = clazz.getMethods();
								for (Method method : methods) {
									UrlEncodedFormFieldMapping mapping = method.getAnnotation(UrlEncodedFormFieldMapping.class);
									if (mapping != null) {
										methodMapping.put(mapping.value(), method);
									}
								}
								methodMappings.put(form.value(), methodMapping);
							}
						}
					}
				}
				break;
			}
		}
	}

	public synchronized Object generate(String formName, String requestBody) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String action = null;
		Class<UrlEncodedFormBean> clazz = beanMappings.get(formName);
		Map<String, Method> methods = methodMappings.get(formName);
		UrlEncodedFormBean formBean = clazz.newInstance();
		String decodedRequestBody = null;
		try {
			decodedRequestBody = URLDecoder.decode(requestBody, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] paramList = decodedRequestBody.split("&");
		for (String param : paramList) {
			int idxSpliter = param.indexOf("=");
			String key = param.substring(0, idxSpliter);
			String value = param.substring(idxSpliter + 1);
			if (key.equals("action")) {
				action = value;
				formBean.setActionName(value);
				continue;
			}
			int idxRwIdLeft = (!action.equals("create")) ? (8 + 1) : (4 + 1); // the position offset at end of string : 'data[row_'
			int idxFirstRwIdRight = key.indexOf("][");
			int rowId = Integer.valueOf(key.substring(idxRwIdLeft, idxFirstRwIdRight));
			Method setMethodForId = methods.get("id");
			setMethodForId.invoke(formBean, rowId);
			if (action.equals("remove")) {
				break;
			}
			int idxLastRwIdRight = key.lastIndexOf("][");
			int idxFieldLeft = idxLastRwIdRight + 2;
			int idxFieldRight = key.length() - 1;
			String fieldName = key.substring(idxFieldLeft, idxFieldRight);
			Method setMethodForField = methods.get(fieldName);
			setMethodForField.invoke(formBean, value);
		}
		return (Object)formBean;
	}

	public synchronized R21fcRcActionForm generateForR21fcRcActionForm(String requestBody) {
		String action = null;
		R21fcRcActionForm formBean = new R21fcRcActionForm();
		String decodedRequestBody = null;
		try {
			decodedRequestBody = URLDecoder.decode(requestBody, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] paramList = decodedRequestBody.split("&");
		for (String param : paramList) {
			int idxSpliter = param.indexOf("=");
			String key = param.substring(0, idxSpliter);
			String value = param.substring(idxSpliter + 1);
			if (key.equals("action")) {
				action = value;
				formBean.setActionName(value);
				continue;
			}
			int idxRwIdLeft = (!action.equals("create")) ? (8 + 1) : (4 + 1); // the position offset at end of string : 'data[row_'
			int idxFirstRwIdRight = key.indexOf("][");
			int rowId = Integer.valueOf(key.substring(idxRwIdLeft, idxFirstRwIdRight));
			formBean.setId(rowId);
			if (action.equals("remove")) {
				break;
			}
			int idxLastRwIdRight = key.lastIndexOf("][");
			int idxFieldLeft = idxLastRwIdRight + 2;
			int idxFieldRight = key.length() - 1;
			String fieldName = key.substring(idxFieldLeft, idxFieldRight);
			if (fieldName.equals("ruleCaseName")) {
				formBean.setRuleCaseName(value);
			} else if (fieldName.equals("ruleCaseOwner")) {
				formBean.setRuleCaseOwner(value);
			} else if (fieldName.equals("ruleCaseBusinessFunction")) {
				formBean.setRuleCaseBusinessFunction(value);
			} else if (fieldName.equals("ruleCaseDescription")) {
				formBean.setRuleCaseDescription(value);
			} else if (fieldName.equals("ruleCaseTargetTable")) {
				formBean.setRuleCaseTargetTable(value);
			} else if (fieldName.equals("ruleCaseTargetField")) {
				formBean.setRuleCaseTargetField(value);
			} else if (fieldName.equals("ruleCaseTargetConditionField")) {
				formBean.setRuleCaseTargetConditionField(value);
			} else if (fieldName.equals("ruleCaseTargetConnectionName")) {
				formBean.setRuleCaseTargetConnectionName(value);
			} else {
				continue;
			}
		}
		return formBean;
	}

	public synchronized R33mcRcActionForm generateForR33mcRcActionForm(String requestBody) {
		String action = null;
		R33mcRcActionForm formBean = new R33mcRcActionForm();
		String decodedRequestBody = null;
		try {
			decodedRequestBody = URLDecoder.decode(requestBody, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] paramList = decodedRequestBody.split("&");
		for (String param : paramList) {
			int idxSpliter = param.indexOf("=");
			String key = param.substring(0, idxSpliter);
			String value = param.substring(idxSpliter + 1);
			if (key.equals("action")) {
				action = value;
				formBean.setActionName(value);
				continue;
			}
			int idxRwIdLeft = (!action.equals("create")) ? (8 + 1) : (4 + 1); // the position offset at end of string : 'data[row_'
			int idxFirstRwIdRight = key.indexOf("][");
			int rowId = Integer.valueOf(key.substring(idxRwIdLeft, idxFirstRwIdRight));
			formBean.setId(rowId);
			if (action.equals("remove")) {
				break;
			}
			int idxLastRwIdRight = key.lastIndexOf("][");
			int idxFieldLeft = idxLastRwIdRight + 2;
			int idxFieldRight = key.length() - 1;
			String fieldName = key.substring(idxFieldLeft, idxFieldRight);
			if (fieldName.equals("ruleCaseName")) {
				formBean.setRuleCaseName(value);
			} else if (fieldName.equals("ruleCaseOwner")) {
				formBean.setRuleCaseOwner(value);
			} else if (fieldName.equals("ruleCaseBusinessFunction")) {
				formBean.setRuleCaseBusinessFunction(value);
			} else if (fieldName.equals("ruleCaseDescription")) {
				formBean.setRuleCaseDescription(value);
			} else if (fieldName.equals("ruleCaseSourceTable")) {
				formBean.setRuleCaseSourceTable(value);
			} else if (fieldName.equals("ruleCaseSourceField")) {
				formBean.setRuleCaseSourceField(value);
			} else if (fieldName.equals("ruleCaseSourceConditionField")) {
				formBean.setRuleCaseSourceConditionField(value);
			} else if (fieldName.equals("ruleCaseSourceConnectionName")) {
				formBean.setRuleCaseSourceConnectionName(value);
			} else if (fieldName.equals("ruleCaseTargetTable")) {
				formBean.setRuleCaseTargetTable(value);
			} else if (fieldName.equals("ruleCaseTargetField")) {
				formBean.setRuleCaseTargetField(value);
			} else if (fieldName.equals("ruleCaseTargetConditionField")) {
				formBean.setRuleCaseTargetConditionField(value);
			} else if (fieldName.equals("ruleCaseTargetConnectionName")) {
				formBean.setRuleCaseTargetConnectionName(value);
			} else {
				continue;
			}
		}
		return formBean;
	}

	public synchronized TcRcActionForm generateForTcRcActionForm(String requestBody) {
		String action = null;
		TcRcActionForm formBean = new TcRcActionForm();
		String decodedRequestBody = null;
		try {
			decodedRequestBody = URLDecoder.decode(requestBody, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] paramList = decodedRequestBody.split("&");
		for (String param : paramList) {
			int idxSpliter = param.indexOf("=");
			String key = param.substring(0, idxSpliter);
			String value = param.substring(idxSpliter + 1);
			if (key.equals("action")) {
				action = value;
				formBean.setActionName(value);
				continue;
			}
			int idxRwIdLeft = (!action.equals("create")) ? (8 + 1) : (4 + 1); // the position offset at end of string : 'data[row_'
			int idxFirstRwIdRight = key.indexOf("][");
			int rowId = Integer.valueOf(key.substring(idxRwIdLeft, idxFirstRwIdRight));
			formBean.setId(rowId);
			if (action.equals("remove")) {
				break;
			}
			int idxLastRwIdRight = key.lastIndexOf("][");
			int idxFieldLeft = idxLastRwIdRight + 2;
			int idxFieldRight = key.length() - 1;
			String fieldName = key.substring(idxFieldLeft, idxFieldRight);
			if (fieldName.equals("ruleCaseDescription")) {
				formBean.setRuleCaseDescription(value);
			} else if (fieldName.equals("ruleCaseOriginalTableSize")) {
				formBean.setRuleCaseOriginalTableSize(value);
			} else if (fieldName.equals("ruleCaseRowsGapGt")) {
				formBean.setRuleCaseRowsGapGt(value);
			} else if (fieldName.equals("ruleCaseRowsGapLt")) {
				formBean.setRuleCaseRowsGapLt(value);
			} else if (fieldName.equals("ruleCaseSeverity")) {
				formBean.setRuleCaseSeverity(value);
			} else {
				continue;
			}
		}
		return formBean;
	}

	public synchronized QsRcActionForm generateForQsRcActionForm(String requestBody) {
		String action = null;
		QsRcActionForm formBean = new QsRcActionForm();
		String decodedRequestBody = null;
		try {
			decodedRequestBody = URLDecoder.decode(requestBody, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] paramList = decodedRequestBody.split("&");
		for (String param : paramList) {
			int idxSpliter = param.indexOf("=");
			String key = param.substring(0, idxSpliter);
			String value = param.substring(idxSpliter + 1);
			if (key.equals("action")) {
				action = value;
				formBean.setActionName(value);
				continue;
			}
			int idxRwIdLeft = (!action.equals("create")) ? (8 + 1) : (4 + 1); // the position offset at end of string : 'data[row_'
			int idxFirstRwIdRight = key.indexOf("][");
			int rowId = Integer.valueOf(key.substring(idxRwIdLeft, idxFirstRwIdRight));
			formBean.setId(rowId);
			if (action.equals("remove")) {
				break;
			}
			int idxLastRwIdRight = key.lastIndexOf("][");
			int idxFieldLeft = idxLastRwIdRight + 2;
			int idxFieldRight = key.length() - 1;
			String fieldName = key.substring(idxFieldLeft, idxFieldRight);
			if (fieldName.equals("ruleCaseDescription")) {
				formBean.setRuleCaseDescription(value);
			} else if (fieldName.equals("ruleCaseSql")) {
				formBean.setRuleCaseSql(value);
			} else if (fieldName.equals("ruleCaseSeverity")) {
				formBean.setRuleCaseSeverity(value);
			} else {
				continue;
			}
		}
		return formBean;
	}
}