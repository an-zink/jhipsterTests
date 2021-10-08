/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { HausComponentsPage, HausDeleteDialog, HausUpdatePage } from './haus.page-object';

const expect = chai.expect;

describe('Haus e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let hausUpdatePage: HausUpdatePage;
  let hausComponentsPage: HausComponentsPage;
  let hausDeleteDialog: HausDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Haus', async () => {
    await navBarPage.goToEntity('haus');
    hausComponentsPage = new HausComponentsPage();
    await browser.wait(ec.visibilityOf(hausComponentsPage.title), 5000);
    expect(await hausComponentsPage.getTitle()).to.eq('jhipsterApp.haus.home.title');
  });

  it('should load create Haus page', async () => {
    await hausComponentsPage.clickOnCreateButton();
    hausUpdatePage = new HausUpdatePage();
    expect(await hausUpdatePage.getPageTitle()).to.eq('jhipsterApp.haus.home.createOrEditLabel');
    await hausUpdatePage.cancel();
  });

  it('should create and save Haus', async () => {
    const nbButtonsBeforeCreate = await hausComponentsPage.countDeleteButtons();

    await hausComponentsPage.clickOnCreateButton();
    await promise.all([hausUpdatePage.setNrInput('nr'), hausUpdatePage.setNameInput('name')]);
    expect(await hausUpdatePage.getNrInput()).to.eq('nr', 'Expected Nr value to be equals to nr');
    expect(await hausUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    await hausUpdatePage.save();
    expect(await hausUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await hausComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Haus', async () => {
    const nbButtonsBeforeDelete = await hausComponentsPage.countDeleteButtons();
    await hausComponentsPage.clickOnLastDeleteButton();

    hausDeleteDialog = new HausDeleteDialog();
    expect(await hausDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.haus.delete.question');
    await hausDeleteDialog.clickOnConfirmButton();

    expect(await hausComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
