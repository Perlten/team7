const RabbitFacade = require('./rabbitFacade')

class BankPerlt {

    BankName = "BankPerlt"

    quickLoan(amount) {
        return amount * 0.1;
    }

    mortgageLoans(amount) {
        return amount * 0.001;
    }

    studentLoans(amount) {
        return amount * 0.01;
    }

    start() {
        const rb = new RabbitFacade();

        rb.subscribe("quick", (body) => {
            const res = {
                name: this.BankName,
                yearlyInterestRate: this.quickLoan(body.amount),
                customer: body.customer,
                amountRequested: body.amount,
                loanType: "quick"
            }
            rb.send("bankResponse", res)
        })

        rb.subscribe("mortgage", (body) => {
            const res = {
                name: this.BankName,
                yearlyInterestRate: this.mortgageLoans(body.amount),
                customer: body.customer,
                amountRequested: body.amount,
                loanType: "mortgage"
            }
            rb.send("bankResponse", res)
        })

        rb.subscribe("student", (body) => {
            const res = {
                name: this.BankName,
                yearlyInterestRate: this.studentLoans(body.amount),
                customer: body.customer,
                amountRequested: body.amount,
                loanType: "student"
            }
            rb.send("bankResponse", res)
        })
    }

}

module.exports = BankPerlt;