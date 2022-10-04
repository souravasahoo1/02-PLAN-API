package in.sourav.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sourav.constants.AppConstants;
import in.sourav.entity.PlanSourav;
import in.sourav.props.AppProperties;
import in.sourav.service.PlanSouravService;

@RestController
public class PlanSouravRestController {

	private PlanSouravService planService;

	private Map<String, String> message;

	public PlanSouravRestController(PlanSouravService planService, AppProperties appProps) {
		this.planService = planService;
		this.message = appProps.getMessages();
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = planService.getCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanSourav plan) {
		String responseMsg = AppConstants.EMPTY_STR;
		boolean isSaved = planService.savePlan(plan);
		if (isSaved) {
			responseMsg = message.get(AppConstants.PLAN_SAVE_SUCC);

		} else {
			responseMsg = message.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<PlanSourav>> plans() {

		List<PlanSourav> allPlans = planService.getAllPlans();

		return new ResponseEntity<>(allPlans, HttpStatus.OK);

	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<PlanSourav> editPlan(@PathVariable Integer planId) {
		PlanSourav plan = planService.getPlanById(planId);

		return new ResponseEntity<>(plan, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);

		String msg = AppConstants.EMPTY_STR;

		if (isDeleted) {
			msg = message.get(AppConstants.PLAN_DELETE_SUCC);

		} else {
			msg = message.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updatePlan(@RequestBody PlanSourav plan) {
		boolean isUpdated = planService.updatePlan(plan);

		String msg = AppConstants.EMPTY_STR;
		if (isUpdated) {
			msg = message.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			msg = message.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(Integer planId, String status) {
		String msg = AppConstants.EMPTY_STR;

		boolean isStatusChange = planService.planStatusChange(planId, status);
		if (isStatusChange) {
			msg = message.get(AppConstants.PLAN_STATUS_CHANGE);
		} else {
			msg = message.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
