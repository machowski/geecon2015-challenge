package pl.allegro.promo.geecon2015.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.allegro.promo.geecon2015.domain.stats.FinancialStatisticsRepository;
import pl.allegro.promo.geecon2015.domain.stats.FinancialStats;
import pl.allegro.promo.geecon2015.domain.transaction.TransactionRepository;
import pl.allegro.promo.geecon2015.domain.transaction.UserTransactions;
import pl.allegro.promo.geecon2015.domain.user.UserRepository;

@Component
public class ReportGenerator {

    private final FinancialStatisticsRepository financialStatisticsRepository;

    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public ReportGenerator(FinancialStatisticsRepository financialStatisticsRepository,
                           UserRepository userRepository,
                           TransactionRepository transactionRepository) {
        this.financialStatisticsRepository = financialStatisticsRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public Report generate(ReportRequest request) {
        FinancialStats minimalIncomeFinancialStats = getFinancialStats(request);
        return createReport(minimalIncomeFinancialStats);
    }

    private Report createReport(FinancialStats financialStats) {
        return financialStats.getUserIds().stream()
            .map(userRepository::detailsOf)
            .map(user -> {
                UserTransactions userTransactions = transactionRepository.transactionsOf(user.getId());
                return new ReportedUser(user.getId(), user.getName(), userTransactions.total());
            })
            .collect(Report::new, Report::add, Report::merge);
    }

    private FinancialStats getFinancialStats(ReportRequest request) {
        return financialStatisticsRepository.listUsersWithMinimalIncome(
            request.getMinimalIncome(),
            request.getUsersToCheck()
        );
    }
}
