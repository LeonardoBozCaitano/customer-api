package br.com.bank.customer.domain.enums;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

public enum MovementType {
    WITHDRAW {
        public BigDecimal execute(BigDecimal balance, BigDecimal amount) {
            return balance.subtract(amount);
        }
    },
    DEPOSIT {
        public BigDecimal execute(BigDecimal balance, BigDecimal amount) {
            return balance.add(amount);
        }
    },
    TRANSFER_IN {
        public BigDecimal execute(BigDecimal balance, BigDecimal amount) {
            return balance.add(amount);
        }
    },
    TRANSFER_OUT {
        public BigDecimal execute(BigDecimal balance, BigDecimal amount) {
            if (balance.compareTo(amount) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            return balance.subtract(amount);
        }
    },
    PAYMENT {
        public BigDecimal execute(BigDecimal balance, BigDecimal amount) {
            return balance.subtract(amount);
        }
    };

    public abstract BigDecimal execute(BigDecimal balance, BigDecimal amount);
}
