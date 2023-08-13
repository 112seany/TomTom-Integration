package com.example.TomTomIntegration.helper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.example.TomTomIntegration.helper.StreamAPIUtil.*;

public class StreamAPIDemo {

    public static void main(String[] args) {

        List<Specialist> specialists = StreamAPIUtil.getSpecialists();

        List<Specialist> engineers = StreamAPIUtil.filterBySpecialty(specialists, Specialty.ENGINEER);
        StreamAPIUtil.printSpecialists(engineers);


        List<Specialist> sortedSpecialistsAsc = StreamAPIUtil.sortSpecialistsByNameAsc(specialists);
        List<Specialist> sortedSpecialistsDesc = StreamAPIUtil.sortSpecialistsByNameDesc(specialists);
        StreamAPIUtil.printSpecialists(sortedSpecialistsAsc);
        StreamAPIUtil.printSpecialists(sortedSpecialistsDesc);


        Specialist withMaxSalary = StreamAPIUtil.findWithMaxSalary(specialists);
        System.out.println(withMaxSalary);

        Specialist withMinSalary = StreamAPIUtil.findWithMinSalary(specialists);
        System.out.println(withMinSalary);

        Map<Specialty, List<Specialist>> groupedBySpecialty = StreamAPIUtil.groupBySpecialty(specialists);
        System.out.println(groupedBySpecialty);

        boolean allEngineers = StreamAPIUtil.matchAllEngineers(specialists);
        System.out.println(allEngineers);

        boolean anyEngineer = StreamAPIUtil.matchAnyEngineer(specialists);
        System.out.println(anyEngineer);

        boolean allSalaryMoreThen1000 = StreamAPIUtil.matchAllSalaryMoreThen(specialists, new BigDecimal(10000));
        System.out.println(allSalaryMoreThen1000);

        boolean noOneWithSalaryMoreThen10000 = StreamAPIUtil.matchNoneSalaryMoreThen(specialists, new BigDecimal(10000));
        System.out.println(noOneWithSalaryMoreThen10000);
    }
}
