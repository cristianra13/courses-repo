import {Component, OnInit, ViewChild} from '@angular/core';
import {PersonInterface} from '../person.interface';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  columns = ['id', 'icon', 'name', 'description', 'createAt'];
// dataSource: PersonInterface[] = [];
  dataSource: MatTableDataSource<PersonInterface>;

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor() { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource ([
      {id: 1, icon: '👨🏻‍🦳', name: 'Cristian Real', description: 'EL mejor', createAt: new Date('07-07-2020')},
      {id: 2, icon: '👨🏽‍🦱', name: 'Carlos', description: 'otro man', createAt: new Date('07-07-2020')},
      {id: 3, icon: '👩🏽‍🦰', name: 'Juana De Arco', description: 'otra mujer', createAt: new Date('07-07-2020')},
      {id: 4, icon: '👩🏿‍🦱', name: 'Maria', description: 'maria algo', createAt: new Date('07-07-2020')}
    ]);
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event){
    const value = (event.target as HTMLInputElement).value;
    this.dataSource.filter = value.trim().toUpperCase();
  }

}
