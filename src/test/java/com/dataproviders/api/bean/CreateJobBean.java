package com.dataproviders.api.bean;

import com.opencsv.bean.CsvBindByName;

public class CreateJobBean {
	
	 
	@CsvBindByName(column = "mst_service_location_id")
	    private String mstServiceLocationId;

	    @CsvBindByName(column = "mst_platform_id")
	    private String mstPlatformId;

	    @CsvBindByName(column = "mst_warrenty_status_id")
	    private String mstWarrentyStatusId;

	    @CsvBindByName(column = "mst_oem_id")
	    private String mstOemId;

	    @CsvBindByName(column = "customer__first_name")
	    private String customerFirstName;

	    @CsvBindByName(column = "customer__last_name")
	    private String customerLastName;

	    @CsvBindByName(column = "customer__mobile_number")
	    private String customerMobileNumber;

	    @CsvBindByName(column = "customer__mobile_number_alt")
	    private String customerMobileNumberAlt;

	    @CsvBindByName(column = "customer__email_id")
	    private String customerEmailId;

	    @CsvBindByName(column = "customer__email_id_alt")
	    private String customerEmailIdAlt;

	    @CsvBindByName(column = "customer_address__flat_number")
	    private String customerAddressFlatNumber;

	    @CsvBindByName(column = "customer_address__apartment_name")
	    private String customerAddressApartmentName;

	    @CsvBindByName(column = "customer_address__street_name")
	    private String customerAddressStreetName;

	    @CsvBindByName(column = "customer_address__landmark")
	    private String customerAddressLandmark;

	    @CsvBindByName(column = "customer_address__area")
	    private String customerAddressArea;

	    @CsvBindByName(column = "customer_address__pincode")
	    private String customerAddressPincode;

	    @CsvBindByName(column = "customer_address__country")
	    private String customerAddressCountry;

	    @CsvBindByName(column = "customer_address__state")
	    private String customerAddressState;

	    @CsvBindByName(column = "customer_product__dop")
	    private String customerProductDop;

	    @CsvBindByName(column = "customer_product__serial_number")
	    private String customerProductSerialNumber;

	    @CsvBindByName(column = "customer_product__imei1")
	    private String customerProductImei1;

	    @CsvBindByName(column = "customer_product__imei2")
	    private String customerProductImei2;

	    @CsvBindByName(column = "customer_product__popurl")
	    private String customerProductPopurl;

	    @CsvBindByName(column = "customer_product__product_id")
	    private String customerProductProductId;

	    @CsvBindByName(column = "customer_product__mst_model_id")
	    private String customerProductMstModelId;

	    @CsvBindByName(column = "problems__id")
	    private String problemsId;

	    @CsvBindByName(column = "problems__remark")
	    private String problemsRemark;
	
	public CreateJobBean() {
		
	}
	
	 public String getMstServiceLocationId() {
			return mstServiceLocationId;
		}

		public void setMstServiceLocationId(String mstServiceLocationId) {
			this.mstServiceLocationId = mstServiceLocationId;
		}

		public String getMstPlatformId() {
			return mstPlatformId;
		}

		public void setMstPlatformId(String mstPlatformId) {
			this.mstPlatformId = mstPlatformId;
		}

		public String getMstWarrentyStatusId() {
			return mstWarrentyStatusId;
		}

		public void setMstWarrentyStatusId(String mstWarrentyStatusId) {
			this.mstWarrentyStatusId = mstWarrentyStatusId;
		}

		public String getMstOemId() {
			return mstOemId;
		}

		public void setMstOemId(String mstOemId) {
			this.mstOemId = mstOemId;
		}

		public String getCustomerFirstName() {
			return customerFirstName;
		}

		public void setCustomerFirstName(String customerFirstName) {
			this.customerFirstName = customerFirstName;
		}

		public String getCustomerLastName() {
			return customerLastName;
		}

		public void setCustomerLastName(String customerLastName) {
			this.customerLastName = customerLastName;
		}

		public String getCustomerMobileNumber() {
			return customerMobileNumber;
		}

		public void setCustomerMobileNumber(String customerMobileNumber) {
			this.customerMobileNumber = customerMobileNumber;
		}

		public String getCustomerMobileNumberAlt() {
			return customerMobileNumberAlt;
		}

		public void setCustomerMobileNumberAlt(String customerMobileNumberAlt) {
			this.customerMobileNumberAlt = customerMobileNumberAlt;
		}

		public String getCustomerEmailId() {
			return customerEmailId;
		}

		public void setCustomerEmailId(String customerEmailId) {
			this.customerEmailId = customerEmailId;
		}

		public String getCustomerEmailIdAlt() {
			return customerEmailIdAlt;
		}

		public void setCustomerEmailIdAlt(String customerEmailIdAlt) {
			this.customerEmailIdAlt = customerEmailIdAlt;
		}

		public String getCustomerAddressFlatNumber() {
			return customerAddressFlatNumber;
		}

		public void setCustomerAddressFlatNumber(String customerAddressFlatNumber) {
			this.customerAddressFlatNumber = customerAddressFlatNumber;
		}

		public String getCustomerAddressApartmentName() {
			return customerAddressApartmentName;
		}

		public void setCustomerAddressApartmentName(String customerAddressApartmentName) {
			this.customerAddressApartmentName = customerAddressApartmentName;
		}

		public String getCustomerAddressStreetName() {
			return customerAddressStreetName;
		}

		public void setCustomerAddressStreetName(String customerAddressStreetName) {
			this.customerAddressStreetName = customerAddressStreetName;
		}

		public String getCustomerAddressLandmark() {
			return customerAddressLandmark;
		}

		public void setCustomerAddressLandmark(String customerAddressLandmark) {
			this.customerAddressLandmark = customerAddressLandmark;
		}

		public String getCustomerAddressArea() {
			return customerAddressArea;
		}

		public void setCustomerAddressArea(String customerAddressArea) {
			this.customerAddressArea = customerAddressArea;
		}

		public String getCustomerAddressPincode() {
			return customerAddressPincode;
		}

		public void setCustomerAddressPincode(String customerAddressPincode) {
			this.customerAddressPincode = customerAddressPincode;
		}

		public String getCustomerAddressCountry() {
			return customerAddressCountry;
		}

		public void setCustomerAddressCountry(String customerAddressCountry) {
			this.customerAddressCountry = customerAddressCountry;
		}

		public String getCustomerAddressState() {
			return customerAddressState;
		}

		public void setCustomerAddressState(String customerAddressState) {
			this.customerAddressState = customerAddressState;
		}

		public String getCustomerProductDop() {
			return customerProductDop;
		}

		public void setCustomerProductDop(String customerProductDop) {
			this.customerProductDop = customerProductDop;
		}

		public String getCustomerProductSerialNumber() {
			return customerProductSerialNumber;
		}

		public void setCustomerProductSerialNumber(String customerProductSerialNumber) {
			this.customerProductSerialNumber = customerProductSerialNumber;
		}

		public String getCustomerProductImei1() {
			return customerProductImei1;
		}

		public void setCustomerProductImei1(String customerProductImei1) {
			this.customerProductImei1 = customerProductImei1;
		}

		public String getCustomerProductImei2() {
			return customerProductImei2;
		}

		public void setCustomerProductImei2(String customerProductImei2) {
			this.customerProductImei2 = customerProductImei2;
		}

		public String getCustomerProductPopurl() {
			return customerProductPopurl;
		}

		public void setCustomerProductPopurl(String customerProductPopurl) {
			this.customerProductPopurl = customerProductPopurl;
		}

		public String getCustomerProductProductId() {
			return customerProductProductId;
		}

		public void setCustomerProductProductId(String customerProductProductId) {
			this.customerProductProductId = customerProductProductId;
		}

		public String getCustomerProductMstModelId() {
			return customerProductMstModelId;
		}

		public void setCustomerProductMstModelId(String customerProductMstModelId) {
			this.customerProductMstModelId = customerProductMstModelId;
		}

		public String getProblemsId() {
			return problemsId;
		}

		public void setProblemsId(String problemsId) {
			this.problemsId = problemsId;
		}

		public String getProblemsRemark() {
			return problemsRemark;
		}

		public void setProblemsRemark(String problemsRemark) {
			this.problemsRemark = problemsRemark;
		}

		@Override
		public String toString() {
			return "CreateJobBean [mstServiceLocationId=" + mstServiceLocationId + ", mstPlatformId=" + mstPlatformId
					+ ", mstWarrentyStatusId=" + mstWarrentyStatusId + ", mstOemId=" + mstOemId + ", customerFirstName="
					+ customerFirstName + ", customerLastName=" + customerLastName + ", customerMobileNumber="
					+ customerMobileNumber + ", customerMobileNumberAlt=" + customerMobileNumberAlt
					+ ", customerEmailId=" + customerEmailId + ", customerEmailIdAlt=" + customerEmailIdAlt
					+ ", customerAddressFlatNumber=" + customerAddressFlatNumber + ", customerAddressApartmentName="
					+ customerAddressApartmentName + ", customerAddressStreetName=" + customerAddressStreetName
					+ ", customerAddressLandmark=" + customerAddressLandmark + ", customerAddressArea="
					+ customerAddressArea + ", customerAddressPincode=" + customerAddressPincode
					+ ", customerAddressCountry=" + customerAddressCountry + ", customerAddressState="
					+ customerAddressState + ", customerProductDop=" + customerProductDop
					+ ", customerProductSerialNumber=" + customerProductSerialNumber + ", customerProductImei1="
					+ customerProductImei1 + ", customerProductImei2=" + customerProductImei2
					+ ", customerProductPopurl=" + customerProductPopurl + ", customerProductProductId="
					+ customerProductProductId + ", customerProductMstModelId=" + customerProductMstModelId
					+ ", problemsId=" + problemsId + ", problemsRemark=" + problemsRemark + "]";
		}

	
	
	

}
