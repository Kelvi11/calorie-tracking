package com.kelvin.calorietracking.service.rs;

import com.kelvin.api.service.RsRepositoryService;
import com.kelvin.calorietracking.model.Bill;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kelvin.calorietracking.managment.AppConstants.BILLS_PATH;

@RestController
@RequestMapping(BILLS_PATH)
public class BillController extends RsRepositoryService<Bill, String> {

    public BillController() {
        super(Bill.class);
    }

    @Override
    protected String getDefaultOrderBy() {
        return "created_date desc";
    }

    @Override
    public void applyFilters() throws Exception {

        if (nn("like.created_by")) {
            getEntityManager().unwrap(Session.class).enableFilter("like.created_by").setParameter("created_by", likeParamToLowerCase("like.created_by"));
        }
    }

    @Override
    protected void prePersist(Bill object) throws Exception {
    }
}
