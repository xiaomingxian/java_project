package pojo.activiti;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActRuTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActRuTaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID_ is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID_ =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID_ <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID_ >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID_ >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID_ <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID_ <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID_ like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID_ not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID_ in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID_ not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID_ between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID_ not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRevIsNull() {
            addCriterion("REV_ is null");
            return (Criteria) this;
        }

        public Criteria andRevIsNotNull() {
            addCriterion("REV_ is not null");
            return (Criteria) this;
        }

        public Criteria andRevEqualTo(Integer value) {
            addCriterion("REV_ =", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevNotEqualTo(Integer value) {
            addCriterion("REV_ <>", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevGreaterThan(Integer value) {
            addCriterion("REV_ >", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevGreaterThanOrEqualTo(Integer value) {
            addCriterion("REV_ >=", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevLessThan(Integer value) {
            addCriterion("REV_ <", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevLessThanOrEqualTo(Integer value) {
            addCriterion("REV_ <=", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevIn(List<Integer> values) {
            addCriterion("REV_ in", values, "rev");
            return (Criteria) this;
        }

        public Criteria andRevNotIn(List<Integer> values) {
            addCriterion("REV_ not in", values, "rev");
            return (Criteria) this;
        }

        public Criteria andRevBetween(Integer value1, Integer value2) {
            addCriterion("REV_ between", value1, value2, "rev");
            return (Criteria) this;
        }

        public Criteria andRevNotBetween(Integer value1, Integer value2) {
            addCriterion("REV_ not between", value1, value2, "rev");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIsNull() {
            addCriterion("EXECUTION_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIsNotNull() {
            addCriterion("EXECUTION_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionIdEqualTo(String value) {
            addCriterion("EXECUTION_ID_ =", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotEqualTo(String value) {
            addCriterion("EXECUTION_ID_ <>", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdGreaterThan(String value) {
            addCriterion("EXECUTION_ID_ >", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXECUTION_ID_ >=", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLessThan(String value) {
            addCriterion("EXECUTION_ID_ <", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLessThanOrEqualTo(String value) {
            addCriterion("EXECUTION_ID_ <=", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLike(String value) {
            addCriterion("EXECUTION_ID_ like", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotLike(String value) {
            addCriterion("EXECUTION_ID_ not like", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIn(List<String> values) {
            addCriterion("EXECUTION_ID_ in", values, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotIn(List<String> values) {
            addCriterion("EXECUTION_ID_ not in", values, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdBetween(String value1, String value2) {
            addCriterion("EXECUTION_ID_ between", value1, value2, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotBetween(String value1, String value2) {
            addCriterion("EXECUTION_ID_ not between", value1, value2, "executionId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIsNull() {
            addCriterion("PROC_INST_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIsNotNull() {
            addCriterion("PROC_INST_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andProcInstIdEqualTo(String value) {
            addCriterion("PROC_INST_ID_ =", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotEqualTo(String value) {
            addCriterion("PROC_INST_ID_ <>", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdGreaterThan(String value) {
            addCriterion("PROC_INST_ID_ >", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_INST_ID_ >=", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLessThan(String value) {
            addCriterion("PROC_INST_ID_ <", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLessThanOrEqualTo(String value) {
            addCriterion("PROC_INST_ID_ <=", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLike(String value) {
            addCriterion("PROC_INST_ID_ like", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotLike(String value) {
            addCriterion("PROC_INST_ID_ not like", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIn(List<String> values) {
            addCriterion("PROC_INST_ID_ in", values, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotIn(List<String> values) {
            addCriterion("PROC_INST_ID_ not in", values, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdBetween(String value1, String value2) {
            addCriterion("PROC_INST_ID_ between", value1, value2, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotBetween(String value1, String value2) {
            addCriterion("PROC_INST_ID_ not between", value1, value2, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIsNull() {
            addCriterion("PROC_DEF_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIsNotNull() {
            addCriterion("PROC_DEF_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ =", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ <>", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThan(String value) {
            addCriterion("PROC_DEF_ID_ >", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ >=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThan(String value) {
            addCriterion("PROC_DEF_ID_ <", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ <=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLike(String value) {
            addCriterion("PROC_DEF_ID_ like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotLike(String value) {
            addCriterion("PROC_DEF_ID_ not like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIn(List<String> values) {
            addCriterion("PROC_DEF_ID_ in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotIn(List<String> values) {
            addCriterion("PROC_DEF_ID_ not in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdBetween(String value1, String value2) {
            addCriterion("PROC_DEF_ID_ between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotBetween(String value1, String value2) {
            addCriterion("PROC_DEF_ID_ not between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME_ is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME_ is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME_ =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME_ <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME_ >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_ >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME_ <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME_ <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME_ like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME_ not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME_ in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME_ not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME_ between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME_ not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdIsNull() {
            addCriterion("PARENT_TASK_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdIsNotNull() {
            addCriterion("PARENT_TASK_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdEqualTo(String value) {
            addCriterion("PARENT_TASK_ID_ =", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdNotEqualTo(String value) {
            addCriterion("PARENT_TASK_ID_ <>", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdGreaterThan(String value) {
            addCriterion("PARENT_TASK_ID_ >", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_TASK_ID_ >=", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdLessThan(String value) {
            addCriterion("PARENT_TASK_ID_ <", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_TASK_ID_ <=", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdLike(String value) {
            addCriterion("PARENT_TASK_ID_ like", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdNotLike(String value) {
            addCriterion("PARENT_TASK_ID_ not like", value, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdIn(List<String> values) {
            addCriterion("PARENT_TASK_ID_ in", values, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdNotIn(List<String> values) {
            addCriterion("PARENT_TASK_ID_ not in", values, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdBetween(String value1, String value2) {
            addCriterion("PARENT_TASK_ID_ between", value1, value2, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andParentTaskIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_TASK_ID_ not between", value1, value2, "parentTaskId");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION_ is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION_ is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION_ =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION_ <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION_ >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION_ >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION_ <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION_ <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION_ like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION_ not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION_ in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION_ not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION_ between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION_ not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIsNull() {
            addCriterion("TASK_DEF_KEY_ is null");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIsNotNull() {
            addCriterion("TASK_DEF_KEY_ is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyEqualTo(String value) {
            addCriterion("TASK_DEF_KEY_ =", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotEqualTo(String value) {
            addCriterion("TASK_DEF_KEY_ <>", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyGreaterThan(String value) {
            addCriterion("TASK_DEF_KEY_ >", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_DEF_KEY_ >=", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLessThan(String value) {
            addCriterion("TASK_DEF_KEY_ <", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLessThanOrEqualTo(String value) {
            addCriterion("TASK_DEF_KEY_ <=", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyLike(String value) {
            addCriterion("TASK_DEF_KEY_ like", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotLike(String value) {
            addCriterion("TASK_DEF_KEY_ not like", value, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyIn(List<String> values) {
            addCriterion("TASK_DEF_KEY_ in", values, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotIn(List<String> values) {
            addCriterion("TASK_DEF_KEY_ not in", values, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyBetween(String value1, String value2) {
            addCriterion("TASK_DEF_KEY_ between", value1, value2, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andTaskDefKeyNotBetween(String value1, String value2) {
            addCriterion("TASK_DEF_KEY_ not between", value1, value2, "taskDefKey");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("OWNER_ is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("OWNER_ is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("OWNER_ =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("OWNER_ <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("OWNER_ >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("OWNER_ >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("OWNER_ <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("OWNER_ <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("OWNER_ like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("OWNER_ not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("OWNER_ in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("OWNER_ not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("OWNER_ between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("OWNER_ not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andAssigneeIsNull() {
            addCriterion("ASSIGNEE_ is null");
            return (Criteria) this;
        }

        public Criteria andAssigneeIsNotNull() {
            addCriterion("ASSIGNEE_ is not null");
            return (Criteria) this;
        }

        public Criteria andAssigneeEqualTo(String value) {
            addCriterion("ASSIGNEE_ =", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotEqualTo(String value) {
            addCriterion("ASSIGNEE_ <>", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeGreaterThan(String value) {
            addCriterion("ASSIGNEE_ >", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGNEE_ >=", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeLessThan(String value) {
            addCriterion("ASSIGNEE_ <", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeLessThanOrEqualTo(String value) {
            addCriterion("ASSIGNEE_ <=", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeLike(String value) {
            addCriterion("ASSIGNEE_ like", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotLike(String value) {
            addCriterion("ASSIGNEE_ not like", value, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeIn(List<String> values) {
            addCriterion("ASSIGNEE_ in", values, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotIn(List<String> values) {
            addCriterion("ASSIGNEE_ not in", values, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeBetween(String value1, String value2) {
            addCriterion("ASSIGNEE_ between", value1, value2, "assignee");
            return (Criteria) this;
        }

        public Criteria andAssigneeNotBetween(String value1, String value2) {
            addCriterion("ASSIGNEE_ not between", value1, value2, "assignee");
            return (Criteria) this;
        }

        public Criteria andDelegationIsNull() {
            addCriterion("DELEGATION_ is null");
            return (Criteria) this;
        }

        public Criteria andDelegationIsNotNull() {
            addCriterion("DELEGATION_ is not null");
            return (Criteria) this;
        }

        public Criteria andDelegationEqualTo(String value) {
            addCriterion("DELEGATION_ =", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationNotEqualTo(String value) {
            addCriterion("DELEGATION_ <>", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationGreaterThan(String value) {
            addCriterion("DELEGATION_ >", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationGreaterThanOrEqualTo(String value) {
            addCriterion("DELEGATION_ >=", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationLessThan(String value) {
            addCriterion("DELEGATION_ <", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationLessThanOrEqualTo(String value) {
            addCriterion("DELEGATION_ <=", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationLike(String value) {
            addCriterion("DELEGATION_ like", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationNotLike(String value) {
            addCriterion("DELEGATION_ not like", value, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationIn(List<String> values) {
            addCriterion("DELEGATION_ in", values, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationNotIn(List<String> values) {
            addCriterion("DELEGATION_ not in", values, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationBetween(String value1, String value2) {
            addCriterion("DELEGATION_ between", value1, value2, "delegation");
            return (Criteria) this;
        }

        public Criteria andDelegationNotBetween(String value1, String value2) {
            addCriterion("DELEGATION_ not between", value1, value2, "delegation");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("PRIORITY_ is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("PRIORITY_ is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("PRIORITY_ =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("PRIORITY_ <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("PRIORITY_ >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRIORITY_ >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("PRIORITY_ <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("PRIORITY_ <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("PRIORITY_ in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("PRIORITY_ not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("PRIORITY_ between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("PRIORITY_ not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME_ is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME_ is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME_ =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME_ <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME_ >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME_ >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME_ <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME_ <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME_ in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME_ not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME_ between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME_ not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNull() {
            addCriterion("DUE_DATE_ is null");
            return (Criteria) this;
        }

        public Criteria andDueDateIsNotNull() {
            addCriterion("DUE_DATE_ is not null");
            return (Criteria) this;
        }

        public Criteria andDueDateEqualTo(Date value) {
            addCriterion("DUE_DATE_ =", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotEqualTo(Date value) {
            addCriterion("DUE_DATE_ <>", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThan(Date value) {
            addCriterion("DUE_DATE_ >", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateGreaterThanOrEqualTo(Date value) {
            addCriterion("DUE_DATE_ >=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThan(Date value) {
            addCriterion("DUE_DATE_ <", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateLessThanOrEqualTo(Date value) {
            addCriterion("DUE_DATE_ <=", value, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateIn(List<Date> values) {
            addCriterion("DUE_DATE_ in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotIn(List<Date> values) {
            addCriterion("DUE_DATE_ not in", values, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateBetween(Date value1, Date value2) {
            addCriterion("DUE_DATE_ between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andDueDateNotBetween(Date value1, Date value2) {
            addCriterion("DUE_DATE_ not between", value1, value2, "dueDate");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("CATEGORY_ is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("CATEGORY_ is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("CATEGORY_ =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("CATEGORY_ <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("CATEGORY_ >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("CATEGORY_ <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("CATEGORY_ like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("CATEGORY_ not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("CATEGORY_ in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("CATEGORY_ not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("CATEGORY_ between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_ not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateIsNull() {
            addCriterion("SUSPENSION_STATE_ is null");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateIsNotNull() {
            addCriterion("SUSPENSION_STATE_ is not null");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateEqualTo(Integer value) {
            addCriterion("SUSPENSION_STATE_ =", value, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateNotEqualTo(Integer value) {
            addCriterion("SUSPENSION_STATE_ <>", value, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateGreaterThan(Integer value) {
            addCriterion("SUSPENSION_STATE_ >", value, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("SUSPENSION_STATE_ >=", value, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateLessThan(Integer value) {
            addCriterion("SUSPENSION_STATE_ <", value, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateLessThanOrEqualTo(Integer value) {
            addCriterion("SUSPENSION_STATE_ <=", value, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateIn(List<Integer> values) {
            addCriterion("SUSPENSION_STATE_ in", values, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateNotIn(List<Integer> values) {
            addCriterion("SUSPENSION_STATE_ not in", values, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateBetween(Integer value1, Integer value2) {
            addCriterion("SUSPENSION_STATE_ between", value1, value2, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andSuspensionStateNotBetween(Integer value1, Integer value2) {
            addCriterion("SUSPENSION_STATE_ not between", value1, value2, "suspensionState");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID_ =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID_ <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID_ >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID_ >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID_ <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID_ <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID_ like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID_ not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID_ in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID_ not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID_ between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID_ not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andFormKeyIsNull() {
            addCriterion("FORM_KEY_ is null");
            return (Criteria) this;
        }

        public Criteria andFormKeyIsNotNull() {
            addCriterion("FORM_KEY_ is not null");
            return (Criteria) this;
        }

        public Criteria andFormKeyEqualTo(String value) {
            addCriterion("FORM_KEY_ =", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyNotEqualTo(String value) {
            addCriterion("FORM_KEY_ <>", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyGreaterThan(String value) {
            addCriterion("FORM_KEY_ >", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_KEY_ >=", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyLessThan(String value) {
            addCriterion("FORM_KEY_ <", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyLessThanOrEqualTo(String value) {
            addCriterion("FORM_KEY_ <=", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyLike(String value) {
            addCriterion("FORM_KEY_ like", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyNotLike(String value) {
            addCriterion("FORM_KEY_ not like", value, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyIn(List<String> values) {
            addCriterion("FORM_KEY_ in", values, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyNotIn(List<String> values) {
            addCriterion("FORM_KEY_ not in", values, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyBetween(String value1, String value2) {
            addCriterion("FORM_KEY_ between", value1, value2, "formKey");
            return (Criteria) this;
        }

        public Criteria andFormKeyNotBetween(String value1, String value2) {
            addCriterion("FORM_KEY_ not between", value1, value2, "formKey");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}