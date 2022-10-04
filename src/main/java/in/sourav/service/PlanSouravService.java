package in.sourav.service;

import java.util.List;
import java.util.Map;

import in.sourav.entity.PlanSourav;

public interface PlanSouravService {
	public Map<Integer, String> getCategories();

	public boolean savePlan(PlanSourav plan);

	public List<PlanSourav> getAllPlans();

	public PlanSourav getPlanById(Integer planId);

	public boolean updatePlan(PlanSourav plan);

	public boolean deletePlanById(Integer planId);

	public boolean planStatusChange(Integer planId, String status);
}
