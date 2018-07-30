package at.f1l2.laboratory.dsm;

/**
 * Simple example for distributed state machine: Bank Account Management Service.
 * 
 * Users access {@link BankAccount} by requesting operations like "deposit", "transfer", and "get-balance".
 * 
 * 
 */
public class DistributedStateMachine {

    public void executeOperation(Operation operation) {

        switch (operation) {

        case DEPOSIT:

            break;

        case TRANSFER:

            break;

        case GET_BALANCE:

            break;

        }
    }
}
