package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IncomeStorage {

    private Map<String, Income> incomeList = new HashMap<>();
    private String fileName = "incomes.json";
    Income income = new Income(300.75, new Date(), EIncomeCategory.LÖN);


    public void addIncome(Income income) {
        incomeList.put(income.getId(), income);
    }

    public boolean removeIncome(String expenseId) {
        return incomeList.remove(incomeId) != null;
    }

    public boolean updateIncome(String incomeId, Income newIncome) {
        if (incomeList.containsKey(incomeId)) {
            incomeList.put(incomeId, newIncome);
            return true;
        } else {
            System.out.println("Ingen utgift med ID " + incomeId + " finns inte");
            return false;
        }
    }

    public Collection<Income> getIncomes() {
        return incomeList.values();
    }

    public void readIncomes(String fileName) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            Income[] readIncomes = gson.fromJson(reader, Income[].class);
            incomeList.clear();
            for (Income income : readIncomes) {
                incomeList.put(income.getId(), income);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveIncomes(String fileName) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(incomeList.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

}
