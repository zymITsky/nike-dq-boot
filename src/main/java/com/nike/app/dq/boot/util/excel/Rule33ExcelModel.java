
package com.nike.app.dq.boot.util.excel;

import java.io.OutputStream;
import java.util.List;

import com.nike.app.dq.boot.common.constant.Rule33Excel;
import com.nike.app.dq.boot.data.entity.RuleR33mcCase;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

public class Rule33ExcelModel extends ExcelModel {

	public Rule33ExcelModel() {
		super();
	}

	public Rule33ExcelModel(OutputStream os) {
		super(os);
	}

	public WritableCellFormat getWcfForTgfh(Colour bgc) {
		WritableCellFormat wcfTgfh = new WritableCellFormat();
		WritableFont wfTgfh = new WritableFont(WritableFont.createFont(Rule33Excel.TABLE_CONTENT_FONT_FAMILY_NAME), 12, WritableFont.BOLD);
		wcfTgfh.setFont(wfTgfh);
		try {
			wcfTgfh.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			wcfTgfh.setAlignment(Alignment.CENTRE);
			wcfTgfh.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfTgfh.setWrap(true);
			if (bgc != null) {
				wcfTgfh.setBackground(bgc);
			}
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcfTgfh;
	}

	@Override
	public WritableCellFormat getWcfForTfh(Colour bgc) {
		WritableCellFormat wcfFieldHead = new WritableCellFormat();
		WritableFont wfFieldHead = new WritableFont(WritableFont.createFont(Rule33Excel.TABLE_CONTENT_FONT_FAMILY_NAME), 10, WritableFont.BOLD);
		wcfFieldHead.setFont(wfFieldHead);
		try {
			wcfFieldHead.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			wcfFieldHead.setAlignment(Alignment.CENTRE);
			wcfFieldHead.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfFieldHead.setWrap(true);
			if (bgc != null) {
				wcfFieldHead.setBackground(bgc);
			}
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcfFieldHead;
	}

	@Override
	public WritableCellFormat getWcfForTbr(Colour color) {
		WritableCellFormat wcfTbr = new WritableCellFormat();
		try {
			WritableFont wfTbr = new WritableFont(WritableFont.createFont(Rule33Excel.TABLE_CONTENT_FONT_FAMILY_NAME), 8, WritableFont.NO_BOLD);
			if (color != null) {
				wfTbr.setColour(color);
				wfTbr.setBoldStyle(WritableFont.BOLD);
			}
			wcfTbr.setFont(wfTbr);
			wcfTbr.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcfTbr.setAlignment(Alignment.LEFT);
			wcfTbr.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfTbr.setWrap(true);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcfTbr;
	}

	public void buildForTableFieldHead(WritableSheet ws) {
		try {
			for (int i = 0; i < Rule33Excel.ARR_COLS_NAME.length; i++) {
				ws.addCell(new Label(i, Rule33Excel.TABLE_FIELD_HEAD_START_ROW_IDX, Rule33Excel.ARR_COLS_NAME[i], getWcfForTfh(null)));
			}
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	public void buildForTableBodyRows(WritableSheet ws, List<RuleR33mcCase> recordList) {
		try {
			for (int i = 0; i < recordList.size(); i++) {
				RuleR33mcCase rc = recordList.get(i);
				ws.addCell(new Number(Rule33Excel.IDX_COL_CASE_NUMBER, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, (int)rc.getRuleCaseId(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_CASE_NAME, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseName(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_CASE_OWNER, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseOwner(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_BUSINESS_FUNCTION, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseBusinessFunction(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_CASE_DESCRIPTION, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseDescription(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_SOURCE_CONNECTION_NAME, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseSourceConnectionName(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_SOURCE_TABLE, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseSourceTable() , getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_SOURCE_FIELD, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseSourceField(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_SOURCE_CONDITION_FIELD, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseSourceConditionField(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_TARGET_CONNECTION_NAME, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseTargetConnectionName(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_TARGET_TABLE, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseTargetTable() , getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_TARGET_FIELD, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseTargetField(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_TARGET_CONDITION_FIELD, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseTargetConditionField(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_LAST_MODIFIED_BY, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseLastModifiedBy(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_LAST_MODIFIED_DATE, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getRuleCaseLastModifiedDateStr(), getWcfForTbr(null)));
				ws.addCell(new Label(Rule33Excel.IDX_COL_LAST_RUN_ONCE, Rule33Excel.TABLE_BODY_ROW_START_ROW_IDX + i, rc.getLastRunResult(), getWcfForTbr(null)));
			}
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}
}