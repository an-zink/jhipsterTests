/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AutoComponentsPage, AutoDeleteDialog, AutoUpdatePage } from './auto.page-object';

const expect = chai.expect;

describe('Auto e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let autoUpdatePage: AutoUpdatePage;
  let autoComponentsPage: AutoComponentsPage;
  let autoDeleteDialog: AutoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Autos', async () => {
    await navBarPage.goToEntity('auto');
    autoComponentsPage = new AutoComponentsPage();
    await browser.wait(ec.visibilityOf(autoComponentsPage.title), 5000);
    expect(await autoComponentsPage.getTitle()).to.eq('jhipsterApp.auto.home.title');
  });

  it('should load create Auto page', async () => {
    await autoComponentsPage.clickOnCreateButton();
    autoUpdatePage = new AutoUpdatePage();
    expect(await autoUpdatePage.getPageTitle()).to.eq('jhipsterApp.auto.home.createOrEditLabel');
    await autoUpdatePage.cancel();
  });

  it('should create and save Autos', async () => {
    const nbButtonsBeforeCreate = await autoComponentsPage.countDeleteButtons();

    await autoComponentsPage.clickOnCreateButton();
    await promise.all([autoUpdatePage.setNameInput('name'), autoUpdatePage.setNumberInput('5'), autoUpdatePage.setModellInput('modell')]);
    expect(await autoUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await autoUpdatePage.getNumberInput()).to.eq('5', 'Expected number value to be equals to 5');
    expect(await autoUpdatePage.getModellInput()).to.eq('modell', 'Expected Modell value to be equals to modell');
    await autoUpdatePage.save();
    expect(await autoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await autoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Auto', async () => {
    const nbButtonsBeforeDelete = await autoComponentsPage.countDeleteButtons();
    await autoComponentsPage.clickOnLastDeleteButton();

    autoDeleteDialog = new AutoDeleteDialog();
    expect(await autoDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.auto.delete.question');
    await autoDeleteDialog.clickOnConfirmButton();

    expect(await autoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
