package forms;

import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;

/**
 *
 * @author Alexey Ovcharov & Artem Solovenko
 */
public class CriteriaForm extends javax.swing.JDialog {

    private int[] criteriaValues = new int[3];
    private boolean allowToReturnValue;

    private boolean allowToCheckValues;

    public CriteriaForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        allowToReturnValue = true;
        allowToCheckValues = false;
    }

    /**
     * Метод установки минимальных процентов для оценок "3","4","5"
     *
     * @param percentTo3 минимальны процент для оценки "3"
     * @param percentTo4 минимальны процент для оценки "4"
     * @param percentTo5 минимальны процент для оценки "5"
     */
    public void setCriteria(int percentTo3, int percentTo4,
            int percentTo5) {
        //Проверяем корректность оценки "3"
        if (percentTo3 > 5 && percentTo3 < percentTo4) {
            criteriaValues[0] = percentTo3;
            spinnerMark3.setValue(percentTo3);
            //Проверяем корректность оценки "4"
            if (percentTo4 > percentTo3 && percentTo4 < percentTo5) {
                criteriaValues[1] = percentTo4;
                spinnerMark4.setValue(percentTo4);
                //Проверяем корректность оценки "5"
                if (percentTo5 > percentTo4 && percentTo5 < 100) {
                    criteriaValues[2] = percentTo5;
                    spinnerMark5.setValue(percentTo5);

                    allowToCheckValues = true;
                }
            }
        }
        if (!allowToCheckValues) {
            defaultCriteriaValues();
        }
    }

    public int[] getCriteria() {
        if (allowToReturnValue) {
            return criteriaValues;
        } else {
            return null;
        }
    }

    private void defaultCriteriaValues() {
        criteriaValues = new int[3];

        criteriaValues[0] = 25;
        criteriaValues[1] = 50;
        criteriaValues[2] = 75;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lMark2 = new javax.swing.JLabel();
        spinnerMark5 = new javax.swing.JSpinner();
        lMark5Percent = new javax.swing.JLabel();
        lMark3 = new javax.swing.JLabel();
        spinnerMark3 = new javax.swing.JSpinner();
        lMark3Percent = new javax.swing.JLabel();
        spinnerMark4 = new javax.swing.JSpinner();
        lMark4Percent = new javax.swing.JLabel();
        lMark5 = new javax.swing.JLabel();
        lMark4 = new javax.swing.JLabel();
        bClose = new javax.swing.JButton();
        bApply = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Изменить критерий оценки");

        lMark2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lMark2.setText("Оценка \"2\", если набрано меньше, чем для оценки \"3\"");

        spinnerMark5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        spinnerMark5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerMark5StateChanged(evt);
            }
        });

        lMark5Percent.setText("%");

        lMark3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lMark3.setText("Оценка \"3\", если набрано меньше, чем для оценки \"4\" и больше, чем:");

        spinnerMark3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        spinnerMark3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerMark3StateChanged(evt);
            }
        });

        lMark3Percent.setText("%");

        spinnerMark4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        spinnerMark4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerMark4StateChanged(evt);
            }
        });

        lMark4Percent.setText("%");

        lMark5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lMark5.setText("Оценка \"5\", если ставиться, если набрано больше, чем:");

        lMark4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lMark4.setText("Оценка \"4\", если набрано меньше, чем для оценки \"5\" и больше, чем:");

        bClose.setText("Закрыть");
        bClose.setPreferredSize(new java.awt.Dimension(100, 23));
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bApply.setText("Изменить");
        bApply.setPreferredSize(new java.awt.Dimension(100, 23));
        bApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lMark5, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerMark5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lMark5Percent))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lMark4, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerMark4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lMark4Percent))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lMark3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerMark3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lMark3Percent))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lMark2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bApply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMark5)
                    .addComponent(spinnerMark5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMark5Percent))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMark4)
                    .addComponent(spinnerMark4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMark4Percent))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMark3)
                    .addComponent(spinnerMark3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMark3Percent))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lMark2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bApply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApplyActionPerformed

        int percentTo3 = (int) spinnerMark3.getValue();
        int percentTo4 = (int) spinnerMark4.getValue();
        int percentTo5 = (int) spinnerMark5.getValue();
        allowToReturnValue = true;

        //Проверка корректности оценки "3"
        if (percentTo3 > 9 && percentTo3 < percentTo4) {
            criteriaValues[0] = percentTo3;

            //Проверка корректности оценки "4"
            if (percentTo4 > percentTo3 && percentTo4 < percentTo5) {
                criteriaValues[1] = percentTo4;

                //Проверка корректности оценки "5"
                if (percentTo5 > percentTo4 && percentTo5 < 100) {
                    criteriaValues[2] = percentTo5;

                    //Закрываем форму
                    this.dispose();

                } else {
                    allowToReturnValue = false;
                    DialogManager.notify("Предупреждение", "Некорректно введена оценка \"5\"! "
                            + "\nУбедитесь, что её значение "
                            + "не превышает границы других оценок",
                            DialogManager.TypeOfMessage.WARNING);
                }

            } else {
                allowToReturnValue = false;
                DialogManager.notify("Предупреждение", "Некорректно введена оценка \"4\"! "
                        + "\nУбедитесь, что её значение "
                        + "не превышает границы других оценок",
                        DialogManager.TypeOfMessage.WARNING);
            }

        } else {
            allowToReturnValue = false;
            DialogManager.notify("Предупреждение", "Некорректно введена оценка \"3\"! "
                    + "\nУбедитесь, что её значение "
                    + "не превышает границы других оценок",
                    DialogManager.TypeOfMessage.WARNING);
        }
    }//GEN-LAST:event_bApplyActionPerformed

    private void spinnerMark5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerMark5StateChanged
        if (allowToCheckValues) {
//            if ((int) spinnerMark5.getValue() < 30) {
//                spinnerMark5.setValue(30);
//            }
            if ((int) spinnerMark5.getValue() > 99) {
                spinnerMark5.setValue(99);
            }
            //Дописать: добавить зависимости от других оценок (не менее "4")
            if ((int) spinnerMark5.getValue() < (int) spinnerMark4.getValue() + 1) {
                spinnerMark5.setValue((int) spinnerMark4.getValue() + 1);
            }
        }
    }//GEN-LAST:event_spinnerMark5StateChanged

    private void spinnerMark4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerMark4StateChanged
        if (allowToCheckValues) {
//            if ((int) spinnerMark4.getValue() < 20) {
//                spinnerMark4.setValue(20);
//            }
//            if ((int) spinnerMark4.getValue() > 90) {
//                spinnerMark4.setValue(90);
//            }
            //Дописать: добавить зависимости от других оценок (не менее "3", не более "5")
            if ((int) spinnerMark4.getValue() > (int) spinnerMark5.getValue() - 1) {
                spinnerMark4.setValue((int) spinnerMark5.getValue() - 1);
            }
            if ((int) spinnerMark4.getValue() < (int) spinnerMark3.getValue() + 1) {
                spinnerMark4.setValue((int) spinnerMark3.getValue() + 1);
            }
        }
    }//GEN-LAST:event_spinnerMark4StateChanged

    private void spinnerMark3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerMark3StateChanged
        if (allowToCheckValues) {
            if ((int) spinnerMark3.getValue() < 10) {
                spinnerMark3.setValue(10);
            }
//            if ((int) spinnerMark3.getValue() > 80) {
//                spinnerMark3.setValue(80);
//            }
            //Дописать: добавить зависимости от других оценок (не менее "2", не более "4")
            if ((int) spinnerMark3.getValue() > (int) spinnerMark4.getValue() - 1) {
                spinnerMark3.setValue((int) spinnerMark4.getValue() - 1);
            }
        }

    }//GEN-LAST:event_spinnerMark3StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bApply;
    private javax.swing.JButton bClose;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lMark2;
    private javax.swing.JLabel lMark3;
    private javax.swing.JLabel lMark3Percent;
    private javax.swing.JLabel lMark4;
    private javax.swing.JLabel lMark4Percent;
    private javax.swing.JLabel lMark5;
    private javax.swing.JLabel lMark5Percent;
    private javax.swing.JSpinner spinnerMark3;
    private javax.swing.JSpinner spinnerMark4;
    private javax.swing.JSpinner spinnerMark5;
    // End of variables declaration//GEN-END:variables

}
