package in.sourav.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sourav.entity.PlanSourav;
import in.sourav.entity.PlanSouravCategories;
import in.sourav.repo.PlanSouravCatagoriesRepo;
import in.sourav.repo.PlanSouravRepo;

@Service
public class PlanSouravServiceImpl implements PlanSouravService {
	@Autowired
	private PlanSouravRepo planRepo;

	@Autowired
	private PlanSouravCatagoriesRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getCategories() {
		List<PlanSouravCategories> categories = planCategoryRepo.findAll();
		Map<Integer, String> categoryMap = new HashMap<>();
		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());

		});

		return categoryMap;
	}

	@Override
	public boolean savePlan(PlanSourav plan) {
		PlanSourav saved = planRepo.save(plan);
//		if (saved.getPlanId() != null) {
//			return true;
//
//		} else {
//			return false;
//		}
//-----------------------------------------------
//		return saved.getPlanId()!=null?true:false;
//-----------------------------------------------
		return saved.getPlanId() != null;
	}

	@Override
	public List<PlanSourav> getAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public PlanSourav getPlanById(Integer planId) {
		Optional<PlanSourav> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(PlanSourav plan) {
		planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<PlanSourav> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			PlanSourav plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;

		}
		return false;
	}

}
