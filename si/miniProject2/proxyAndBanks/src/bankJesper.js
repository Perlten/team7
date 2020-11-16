const RabbitFacade = require('./rabbitFacade')

class BankJesper {
    bankName = "BankJesper";


    quickLoan(amount) {
        return amount * 0.001;
    }

    mortgageLoans(amount) {
        return amount * 0.00001;
    }

    studentLoans(amount) {
        return amount * 0.0001;
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

module.exports = BankJesper;