package com.orasi.utils;

import org.openqa.selenium.Keys;

public final class Widgets {

	public static final String SIGN_IN = "/html/body/center/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/div/table/tbody/tr[4]/td/input";
	//*[@id="completeMessage"]
	
	
	public static final String monitorIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td/table/tbody/tr/td[2]/form/a/img";
	public static final String detailsIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td/table/tbody/tr/td[2]/form/a[2]/img";
	public static final String searchIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td/table/tbody/tr/td[2]/form/a[3]/img";
	public static final String statusIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td/table/tbody/tr/td[2]/form/a[4]/img";
	public static final String escalateIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td/table/tbody/tr/td[2]/form/a[5]/img";
	public static final String secondMonitorIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td/table/tbody/tr/td[2]/form/a[6]/img";
	public static final String viewDataIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td/table/tbody/tr/td[2]/form/a[7]/img";
	public static final String refreshIconTR = "/html/body/div/table/tbody/tr/td/table/tbody/tr/th[4]/table/tbody/tr/td[2]/table/tbody/tr/td[2]/a[4]/img";
	public static final String menuListIconTR = "//*[@id='menuListIcon']";
	
	public static final String ColumnHeaders = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th";
	public static final String caseNumCol = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[2]/a/b";

	//IVD Menu Columns
	public static final String letterDateCol_IVD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[3]/a";
	public static final String vetNameCol_IVD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[4]/a";
	public static final String vetIcnCol_IVD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[5]/a";
	public static final String statusCol_IVD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[6]/a";
	public static final String initCatCol_IVD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[7]/a";
	public static final String seelctedOptCol_IVD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[8]/a";
	public static final String processIdCol_IVD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[9]/a";
	
	//IVD Table Entries
	public static final String firstIVDCase = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr[2]/td[2]";	
	
	//BSD Menu Columns
	public static final String requestIdCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[2]/a";
	public static final String originatorNameCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[3]/a";
	public static final String createdDateCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[4]/a";
	public static final String currentActivityCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[5]/a";
	public static final String itemTotalCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[6]"; // Not sortable
	public static final String statusCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[7]/a";
	public static final String nameCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[2]/a";
	public static final String descriptionCol_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr/th[3]/a";
	
	public static final String firstProcessInitiationEntry_BSD = "/html/body/div/table/tbody/tr[2]/td/form/table/tbody/tr[2]/td[2]/a";

	//BSD Activity Page
	public static final String helpButton_BSD = "/html/body/div/div/form/div/table/tbody/tr/td[4]/div[2]/a";
	public static final String showVendorInfoButton_BSD = "/html/body/div/div/form/div/div[2]/div[2]/div[2]/div/div/div/div/div/div/div/div/div/div[6]/div/span/a";
	public static final String vendorPopupSaveButton_BSD = "/html/body/div[8]/div[2]/div/button";
	public static final String vendorPopupCancelButton_BSD = "/html/body/div[8]/div[2]/div/button[2]";
	public static final String itemPopupSaveButton_BSD = "/html/body/div[7]/div[3]/div/button";
	public static final String itemPopupCancelButton_BSD = "/html/body/div[7]/div[3]/div/button";
	
	public static final String rcmMonitor = "/html/body/div/table/tbody/tr/td[2]";
	//*[@id="monitor"]
	public static final String rcmMonitorCSS = "html body div#mainWrapper table#bctable1000233 tbody tr td form table.list tbody tr.portlet-table-alternate td a";
	
	public static final String ctrlT = Keys.chord(Keys.CONTROL, "t");
	
}
