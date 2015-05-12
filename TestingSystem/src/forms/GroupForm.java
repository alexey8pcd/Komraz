package forms;

import entities.Gruppa;
import entities.Student;
import entities.StudentTest;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author Артем
 */
public class GroupForm extends javax.swing.JDialog {

    private List<Gruppa> groups;
    private List<Student> students;
    private final ListModel GROUPS_LIST_MODEL = new AbstractListModel() {

        @Override
        public int getSize() {
            return groups.size();
        }

        @Override
        public Object getElementAt(int index) {
            return groups.get(index).getNazvanie();
        }
    };
    private final TableModel STUDENTS_TABLE_MODEL = new AbstractTableModel() {

        @Override
        public int getRowCount() {
            if (students != null) {
                entityManager.getTransaction().begin();
                TypedQuery<Student> queryForStudents = entityManager.
                        createQuery("SELECT s FROM Student s "
                                + "WHERE s.gruppaIdGruppa.idGruppa=:idGruppa",
                                Student.class);
                int selectedIndex = listGroups.getSelectedIndex();
                Gruppa group = groups.get(selectedIndex);
                queryForStudents.setParameter("idGruppa", group.getIdGruppa());
                students = queryForStudents.getResultList();
                entityManager.getTransaction().commit();
                return students.size();
            } else {
                return 0;
            }
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return students.get(rowIndex).getFio();
            } else {
                return students.get(rowIndex).getLogin();
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public GroupForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);

        listGroups.setModel(GROUPS_LIST_MODEL);
        tableListOfStudents.setModel(STUDENTS_TABLE_MODEL);
        groups = entityManager.createNamedQuery("Gruppa.findAll",
                Gruppa.class).getResultList();

        JTableHeader header = tableListOfStudents.getTableHeader();
        header.getColumnModel().getColumn(0).setWidth(200);
        header.getColumnModel().getColumn(0).setHeaderValue("ФИО");
        header.getColumnModel().getColumn(1).setHeaderValue("Логин");

        tableListOfStudents.setTableHeader(header);
        tableListOfStudents.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableListOfStudents.getColumnModel().getColumn(0).setPreferredWidth(250);
        tableListOfStudents.getColumnModel().getColumn(1).setPreferredWidth(
                tableListOfStudents.getWidth() - 250);
        if (groups != null) {
            listGroups.updateUI();
            listGroups.setSelectedIndex(0);

            entityManager.getTransaction().begin();
            TypedQuery<Student> queryForStudents = entityManager.
                    createQuery("SELECT s FROM Student s "
                            + "WHERE s.gruppaIdGruppa.idGruppa=:idGruppa",
                            Student.class);
            int selectedIndex = listGroups.getSelectedIndex();
            Gruppa group = groups.get(selectedIndex);
            queryForStudents.setParameter("idGruppa", group.getIdGruppa());
            students = queryForStudents.getResultList();
            entityManager.getTransaction().commit();
            if (!students.isEmpty()) {
                tableListOfStudents.updateUI();
                //Сделать первого студента "выбранным"
                tableListOfStudents.setRowSelectionInterval(0, 0);
            }
        }
    }

    private void refresh() {
        groups = entityManager.createNamedQuery("Gruppa.findAll",
                Gruppa.class).getResultList();

        listGroups.updateUI();
        entityManager.getTransaction().begin();
        TypedQuery<Student> queryForStudents = entityManager.
                createQuery("SELECT s FROM Student s "
                        + "WHERE s.gruppaIdGruppa.idGruppa=:idGruppa",
                        Student.class);
        int selectedIndex = listGroups.getSelectedIndex();
        Gruppa group = groups.get(selectedIndex);
        queryForStudents.setParameter("idGruppa", group.getIdGruppa());
        students = queryForStudents.getResultList();
        entityManager.getTransaction().commit();
        tableListOfStudents.updateUI();
        if (!students.isEmpty()) {
            tableListOfStudents.setRowSelectionInterval(0, 0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sPaneForListGroups = new javax.swing.JScrollPane();
        listGroups = new javax.swing.JList();
        lGroups = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableListOfStudents = new javax.swing.JTable();
        bCreateGroup = new javax.swing.JButton();
        bDeleteGroup = new javax.swing.JButton();
        bEditGroup = new javax.swing.JButton();
        bCreateStudent = new javax.swing.JButton();
        bEditStudent = new javax.swing.JButton();
        bDeleteStudent = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        bMoveStudent = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Группы и студенты");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        listGroups.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listGroupsValueChanged(evt);
            }
        });
        sPaneForListGroups.setViewportView(listGroups);

        lGroups.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lGroups.setText("Группы");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Студенты");

        tableListOfStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ФИО", "Логин"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableListOfStudents.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableListOfStudents);
        tableListOfStudents.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tableListOfStudents.getColumnModel().getColumnCount() > 0) {
            tableListOfStudents.getColumnModel().getColumn(0).setMinWidth(180);
            tableListOfStudents.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableListOfStudents.getColumnModel().getColumn(0).setMaxWidth(220);
        }

        bCreateGroup.setText("Создать");
        bCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateGroupActionPerformed(evt);
            }
        });

        bDeleteGroup.setText("Удалить");
        bDeleteGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteGroupActionPerformed(evt);
            }
        });

        bEditGroup.setText("Редактировать");
        bEditGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditGroupActionPerformed(evt);
            }
        });

        bCreateStudent.setText("Создать");
        bCreateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateStudentActionPerformed(evt);
            }
        });

        bEditStudent.setText("Редактировать");
        bEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditStudentActionPerformed(evt);
            }
        });

        bDeleteStudent.setText("Удалить");
        bDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteStudentActionPerformed(evt);
            }
        });

        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bMoveStudent.setText("<html><center>Изменить группу");
        bMoveStudent.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lGroups)
                                .addGap(308, 308, 308)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(bCreateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                        .addComponent(bEditStudent)
                                        .addGap(18, 18, 18)
                                        .addComponent(bMoveStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(sPaneForListGroups, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(bCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bEditGroup)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bDeleteGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2)))))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lGroups)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sPaneForListGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bEditGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDeleteGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCreateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bMoveStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bEditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditStudentActionPerformed

        if (!students.isEmpty()) {
            int selectedIndex = listGroups.getSelectedIndex();
            Gruppa group = groups.get(selectedIndex);
            int selectedStudent = tableListOfStudents.getSelectedRow();

            EditStudentForm editGroupForm = new EditStudentForm(null, true);
            editGroupForm.setGroup(group);
            editGroupForm.setStudent(students.get(selectedStudent));
            editGroupForm.setVisible(true);
            refresh();
        } else {
            DialogManager.warningMessage("Ошибка", "Студенты отсутствуют!", DialogManager.TypeOfMessage.ERROR);
        }

    }//GEN-LAST:event_bEditStudentActionPerformed

    private void bEditGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditGroupActionPerformed

        if (!groups.isEmpty()) {
            int selected = listGroups.getSelectedIndex();
            EditGroupForm editGroupForm = new EditGroupForm(null, true);
            editGroupForm.setGroup(groups.get(selected));
            editGroupForm.setVisible(true);
            refresh();
        } else {
            JOptionPane.showMessageDialog(null, "Группы отсутствуют. \n"
                    + "Необходимо создать группу",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bEditGroupActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed

        this.dispose();

    }//GEN-LAST:event_bCloseActionPerformed

    private void bCreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateGroupActionPerformed

        EditGroupForm editGroupForm = new EditGroupForm(null, true);
        editGroupForm.setVisible(true);
        refresh();

    }//GEN-LAST:event_bCreateGroupActionPerformed

    private void bDeleteGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteGroupActionPerformed
        int selectedIndex = listGroups.getSelectedIndex();
        if (selectedIndex < GROUPS_LIST_MODEL.getSize()
                && selectedIndex >= 0) {
            //Удаляем выбранную группу из списка
            int groupsSize = groups.size();
            Gruppa delGroup = groups.get(selectedIndex);

            if (delGroup != null) {
                TypedQuery<Student> queryForRelativeStudents
                        = entityManager.createQuery("SELECT s FROM Student s "
                                + "WHERE s.gruppaIdGruppa.idGruppa = :id", Student.class);
                queryForRelativeStudents.setParameter("id", delGroup.getIdGruppa());
                List<Student> relativeStudents = queryForRelativeStudents.getResultList();
                if (relativeStudents.isEmpty()) {
                    try {

//                        entityManager.getTransaction().begin();
//                        entityManager.remove(delGroup);
//                        entityManager.getTransaction().commit();
                        entityManager.getTransaction().begin();
                        Query query = entityManager.createQuery(
                                "DELETE FROM Gruppa v WHERE v.idGruppa=:id");
                        query.setParameter("id", delGroup.getIdGruppa());
                        query.executeUpdate();
                        entityManager.getTransaction().commit();

                        --groupsSize;
                        if (groupsSize > 0) {
                            if (selectedIndex >= groupsSize) {
                                --selectedIndex;
                            }
                            listGroups.setSelectedIndex(selectedIndex);
                            refresh();
                        }
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                } else {
                    DialogManager.warningMessage("Ошибка",
                            "В данной группу содержатся студенты!",
                            DialogManager.TypeOfMessage.ERROR);
                }
            }
        } else {
            DialogManager.warningMessage("Ошибка", "Группа не выбрана!", DialogManager.TypeOfMessage.ERROR);
        }
    }//GEN-LAST:event_bDeleteGroupActionPerformed

    private void bCreateStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateStudentActionPerformed

        int selectedIndex = listGroups.getSelectedIndex();
        Gruppa group = groups.get(selectedIndex);

        EditStudentForm editStudentForm = new EditStudentForm(null, true);
        editStudentForm.setGroup(group);
        editStudentForm.setVisible(true);
        refresh();

    }//GEN-LAST:event_bCreateStudentActionPerformed

    private void bDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteStudentActionPerformed

        if (DialogManager.confirmDeleting("Вы действительно хотите удалить выбранного студента?")) {

            int selectedIndex = tableListOfStudents.getSelectedRow();
            if (selectedIndex < STUDENTS_TABLE_MODEL.getRowCount()
                    && selectedIndex >= 0) {
                //Удаляем выбранного студента из списка
                Student delStudent = students.get(selectedIndex);
                StudentTest studentTest = null;

                if (delStudent != null) {
                    if (!delStudent.getStudentTestList().isEmpty()) {
                        studentTest = delStudent.getStudentTestList().get(0);
                    }
                    if (delStudent.getStudentTestList().isEmpty()) {
                        try {
                            if (studentTest != null) {
                                entityManager.getTransaction().begin();
                                entityManager.remove(studentTest);
                                entityManager.getTransaction().commit();
                            }
                            entityManager.getTransaction().begin();
                            Query query = entityManager.createQuery(
                                    "DELETE FROM Student s WHERE s.idStudent=:id");
                            query.setParameter("id", delStudent.getIdStudent());
                            query.executeUpdate();
                            entityManager.getTransaction().commit();
                            listGroups.setSelectedIndex(0);
                            refresh();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.toString(),
                                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "В данной группе "
                                + "содержатся студенты!",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Студент не выбран",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_bDeleteStudentActionPerformed

    private void listGroupsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listGroupsValueChanged

        refresh();
    }//GEN-LAST:event_listGroupsValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCreateGroup;
    private javax.swing.JButton bCreateStudent;
    private javax.swing.JButton bDeleteGroup;
    private javax.swing.JButton bDeleteStudent;
    private javax.swing.JButton bEditGroup;
    private javax.swing.JButton bEditStudent;
    private javax.swing.JButton bMoveStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lGroups;
    private javax.swing.JList listGroups;
    private javax.swing.JScrollPane sPaneForListGroups;
    private javax.swing.JTable tableListOfStudents;
    // End of variables declaration//GEN-END:variables
}
