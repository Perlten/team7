const RabbitFacade = require('./rabbitFacade')

class BankDue {

    bankName = "BankDue"

    quickLoan(amount) {
        return amount * 0.01;
    }

    mortgageLoans(amount) {
        return amount * 0.0001;
    }

    studentLoans(amount) {
        return amount * 0.001;
    }

    start() {
        const rb = new RabbitFacade();

        rb.subscribe("quick", (body) => {
            const res = {
                name: this.bankName,
                yearlyInterestRate: this.quickLoan(body.amount),
                customer: body.customer,
                amountRequested: body.amount,
                loanType: "quick"
            }
            rb.send("bankResponse", res)
        })

        rb.subscribe("mortgage", (amount) => {
            const res = {
                name: this.bankName,
                yearlyInterestRate: this.mortgageLoans(body.amount),
                customer: body.customer,
                amountRequested: body.amount,
                loanType: "mortgage"
            }
            rb.send("bankResponse", res)
        })

        rb.subscribe("student", (amount) => {
            const res = {
                name: this.bankName,
                yearlyInterestRate: this.studentLoans(body.amount),
                customer: body.customer,
                amountRequested: body.amount,
                loanType: "student"
            }
            rb.send("bankResponse", res)
        })
    }

}

module.exports = BankDue;