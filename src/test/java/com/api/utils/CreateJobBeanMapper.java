package com.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobBeanMapper {

	// we will give bean to this mapper and it will conver createJob payload

	private CreateJobBeanMapper() {
	};

	public static CreateJobPayload mapper(CreateJobBean bean) {

		// bean to createjobPayload object
		int mst_service_location_id = Integer.parseInt(bean.getMstServiceLocationId());
		int mst_platform_id = Integer.parseInt(bean.getMstPlatformId());
		int mst_warrenty_status_id = Integer.parseInt(bean.getMstWarrentyStatusId());
		int mst_oem_id = Integer.parseInt(bean.getMstOemId());

		Customer customer = new Customer(bean.getCustomerFirstName(), bean.getCustomerLastName(),
				bean.getCustomerMobileNumber(), bean.getCustomerMobileNumberAlt(), bean.getCustomerEmailId(),
				bean.getCustomerEmailIdAlt());

		CustomerAdress customerAddess = new CustomerAdress(bean.getCustomerAddressFlatNumber(),
				bean.getCustomerAddressApartmentName(), bean.getCustomerAddressStreetName(),
				bean.getCustomerAddressLandmark(), bean.getCustomerAddressArea(), bean.getCustomerAddressPincode(),
				bean.getCustomerAddressCountry(), bean.getCustomerAddressState());

		int product_id = Integer.parseInt(bean.getCustomerProductProductId());
		int mst_model_id = Integer.parseInt(bean.getCustomerProductMstModelId());

		CustomerProduct customerProduct = new CustomerProduct(bean.getCustomerProductDop(),
				bean.getCustomerProductSerialNumber(), bean.getCustomerProductImei1(), bean.getCustomerProductImei2(),
				bean.getCustomerProductPopurl(), product_id, mst_model_id);

		List<Problems> problemsList = new ArrayList<>();
		int id = Integer.parseInt(bean.getProblemsId());

		Problems problems = new Problems(id, bean.getProblemsRemark());
		problemsList.add(problems);

		CreateJobPayload payload = new CreateJobPayload(mst_service_location_id, mst_platform_id,
				mst_warrenty_status_id, mst_oem_id, customer, customerAddess, customerProduct, problemsList);

		return payload;

	}

}
