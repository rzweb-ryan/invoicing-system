package auth.emp.vo;

import utils.base.BaseQueryModel;
import utils.format.FormatUtil;

/**
 * Created by RZ on 6/30/16.
 */
public class EmpQueryModel extends EmpModel implements BaseQueryModel {
    private Long birthday2;
    private String birthday2View;

    public String getBirthday2View() {
        return birthday2View;
    }

    public Long getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Long birthday2) {
        birthday2View = FormatUtil.formatDate(birthday2);
        this.birthday2 = birthday2;
    }
}
