package streamlearn.imperativetofunctional;

import java.util.function.Predicate;

class Applicant {
    public boolean isCredible() {
        return true;
    }

    public int getCreditScore() {
        return 700;
    }

    public int getEmploymentYears() {
        return 10;
    }

    public boolean hasCriminalRecord() {
        return true;
    }
}

interface Evaluator {
    boolean evaluate(Applicant applicant);
}

class QualifiedEvaluator implements Evaluator {
    public boolean evaluate(Applicant applicant) {
        return applicant.isCredible();
    }
}

class EvaluatorChain implements Evaluator {
    private Evaluator next;

    public EvaluatorChain(Evaluator nextEvaluator) {
        next = nextEvaluator;
    }

    public boolean evaluate(Applicant applicant) {
        return next.evaluate(applicant);
    }
}

class CreditEvaluator extends EvaluatorChain {
    public CreditEvaluator(Evaluator next) {
        super(next);
    }

    public boolean evaluate(Applicant applicant) {
        if (applicant.getCreditScore() > 600)
            return super.evaluate(applicant);
        return false;
    }
}

class EmploymentEvaluator extends EvaluatorChain {
    public EmploymentEvaluator(Evaluator next) {
        super(next);
    }

    public boolean evaluate(Applicant applicant) {
        if (applicant.getEmploymentYears() > 0)
            return super.evaluate(applicant);
        return false;
    }
}

class CriminalRecordsEvaluator extends EvaluatorChain {
    public CriminalRecordsEvaluator(Evaluator next) {
        super(next);
    }

    public boolean evaluate(Applicant applicant) {
        if (!applicant.hasCriminalRecord())
            return super.evaluate(applicant);
        return false;
    }
}

class Imperative {
    public static void evaluate(Applicant applicant, Evaluator evaluator) {
        String result = evaluator.evaluate(applicant) ? "accepted" : "rejected";
        System.out.println("Result of evaluating applicant: " + result);
    }

    public static void main(String[] args) {
        Applicant applicant = new Applicant();
        evaluate(applicant, new CreditEvaluator(new QualifiedEvaluator()));

        evaluate(applicant,
                new CreditEvaluator(new EmploymentEvaluator(new QualifiedEvaluator())));

        evaluate(applicant,
                new CriminalRecordsEvaluator(
                        new EmploymentEvaluator(new QualifiedEvaluator())));

        evaluate(applicant,
                new CriminalRecordsEvaluator(
                        new CreditEvaluator(
                                new EmploymentEvaluator(new QualifiedEvaluator()))));
    }
}

class Functional {
    public static void evaluate(
            Applicant applicant, Predicate<Applicant> evaluator) {

        String result =
                applicant.isCredible() && evaluator.test(applicant) ?
                        "accepted" : "rejected";

        System.out.println("Result of evaluating applicant: " + result);
    }

    public static void main(String[] args) {
        Applicant applicant = new Applicant();

        Predicate<Applicant> creditCheck =
                theApplicant -> theApplicant.getCreditScore() > 600;
        Predicate<Applicant> employmentCheck =
                theApplicant -> theApplicant.getEmploymentYears() > 0;
        Predicate<Applicant> crimeCheck =
                theApplicant -> !theApplicant.hasCriminalRecord();

        evaluate(applicant, creditCheck);

        evaluate(applicant, creditCheck.and(employmentCheck));

        evaluate(applicant, crimeCheck.and(employmentCheck));

        evaluate(applicant, crimeCheck.and(creditCheck).and(employmentCheck));
    }
}